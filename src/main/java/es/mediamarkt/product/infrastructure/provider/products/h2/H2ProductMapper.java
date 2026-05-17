package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.model.ProductLongDescription;
import es.mediamarkt.product.domain.products.model.ProductName;
import es.mediamarkt.product.domain.products.model.ProductShortDescription;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class H2ProductMapper {

    public Product toDomain(H2Product entity) {
        var categoryIds = entity.getCategoryIds().stream()
                .map(CategoryId::of)
                .collect(Collectors.toSet());
        return Product.rehydrate(
                ProductId.of(entity.getId()),
                ProductName.of(entity.getName()),
                entity.getOnlineStatus(),
                ProductLongDescription.of(entity.getLongDescription()),
                ProductShortDescription.of(entity.getShortDescription()),
                categoryIds
        );
    }

    public H2Product toInfra(Product aggregate) {
        var categoryIds = aggregate.categoryIds().stream()
                .map(CategoryId::value)
                .collect(Collectors.toSet());
        return new H2Product(
                aggregate.id().value(),
                aggregate.name().value(),
                aggregate.onlineStatus(),
                aggregate.longDescription().value(),
                aggregate.shortDescription().value(),
                categoryIds
        );
    }

    public FullyCategorizedProduct toFullyCategorizedView(Product aggregate, List<H2Category> categories) {
        var categoryNameById = categories.stream()
                .collect(Collectors.toMap(H2Category::getId, H2Category::getName));
        var categoryIds = aggregate.categoryIds().stream()
                .map(CategoryId::value)
                .sorted()
                .toList();
        var categoryName = aggregate.categoryIds().stream()
                .map(CategoryId::value)
                .sorted()
                .map(categoryNameById::get)
                .filter(name -> name != null && !name.isBlank())
                .collect(Collectors.joining(", "));
        return new FullyCategorizedProduct(
                aggregate.id().value(),
                aggregate.name().value(),
                aggregate.onlineStatus().name(),
                aggregate.longDescription().value(),
                aggregate.shortDescription().value(),
                categoryIds,
                categoryName
        );
    }

}
