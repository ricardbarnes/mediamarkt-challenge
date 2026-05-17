package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import org.springframework.stereotype.Component;

@Component
public final class H2CategoryMapper {

    public Category toDomain(H2Category entity) {
        return Category.rehydrate(
                CategoryId.of(entity.getId()),
                CategoryName.of(entity.getName()),
                CatalogId.of(entity.getCatalogId())
        );
    }

    public H2Category toInfra(Category category) {
        return new H2Category(
                category.id().value(),
                category.name().value(),
                category.catalogId().value()
        );
    }

}
