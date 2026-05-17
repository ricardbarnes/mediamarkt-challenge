package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.port.ProductViewRepository;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2Category;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2CategoryRepository;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductViewH2Repository implements ProductViewRepository {

    private final H2ProductRepository productRepository;

    private final H2CategoryRepository categoryRepository;

    private final H2ProductMapper mapper;

    @Override
    public PagedResult<FullyCategorizedProduct> findFullyCategorizedPaginated(PageRequest pageRequest) {
        var pageable = Pageable.ofSize(pageRequest.size()).withPage(pageRequest.page());
        var page = productRepository.findAll(pageable);
        var products = page.getContent().stream()
                .map(mapper::toDomain)
                .toList();
        var categories = loadCategoriesForProducts(products);
        var data = products.stream()
                .map(product -> mapper.toFullyCategorizedView(product, categories))
                .toList();
        return PagedResult.of(data, pageRequest, page.getTotalElements());
    }

    @Override
    public FullyCategorizedProduct findFullyCategorizedById(ProductId value) {
        var product = productRepository.findById(value.value())
                .map(mapper::toDomain)
                .orElseThrow(() -> ProductNotFoundError.becauseOf(value));
        var categories = loadCategoriesForProduct(product);
        return mapper.toFullyCategorizedView(product, categories);
    }

    private List<H2Category> loadCategoriesForProduct(Product product) {
        var categoryIds = product.categoryIds().stream()
                .map(CategoryId::value)
                .toList();
        if (categoryIds.isEmpty()) {
            return List.of();
        }
        return categoryRepository.findByIdIn(categoryIds);
    }

    private List<H2Category> loadCategoriesForProducts(List<Product> products) {
        Set<Long> categoryIds = new HashSet<>();
        for (var product : products) {
            product.categoryIds().forEach(id -> categoryIds.add(id.value()));
        }
        if (categoryIds.isEmpty()) {
            return List.of();
        }
        return new ArrayList<>(categoryRepository.findByIdIn(categoryIds));
    }

}
