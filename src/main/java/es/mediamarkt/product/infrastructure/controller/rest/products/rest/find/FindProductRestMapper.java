package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.PagedResult;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class FindProductRestMapper {

    public FindProductByIdRestResponse toFindByIdResponse(Product product) {
        return new FindProductByIdRestResponse(
                product.id().value(),
                product.name().value(),
                product.onlineStatus().name(),
                product.longDescription().value(),
                product.shortDescription().value(),
                product.categoryIds().stream()
                        .map(CategoryId::value)
                        .collect(Collectors.toSet())
        );
    }

    public FindAllProductsRestResponse toFindAllResponse(PagedResult<Product> pagedResult) {
        var data = pagedResult.data().stream()
                .map(this::toListItemResponse)
                .toList();
        return new FindAllProductsRestResponse(
                data,
                pagedResult.currentPage(),
                pagedResult.pageSize(),
                pagedResult.totalElements(),
                pagedResult.totalPages()
        );
    }

    public FindFullyCategorizedProductByIdRestResponse toFindFullyCategorizedByIdResponse(
            FullyCategorizedProduct product
    ) {
        return new FindFullyCategorizedProductByIdRestResponse(
                product.id(),
                product.name(),
                product.onlineStatus(),
                product.longDescription(),
                product.shortDescription(),
                product.categoryIds(),
                product.categoryName()
        );
    }

    public FindAllFullyCategorizedProductsRestResponse toFindAllFullyCategorizedResponse(
            PagedResult<FullyCategorizedProduct> pagedResult
    ) {
        var data = pagedResult.data().stream()
                .map(this::toFullyCategorizedListItemResponse)
                .toList();
        return new FindAllFullyCategorizedProductsRestResponse(
                data,
                pagedResult.currentPage(),
                pagedResult.pageSize(),
                pagedResult.totalElements(),
                pagedResult.totalPages()
        );
    }

    private FindFullyCategorizedProductListItemRestResponse toFullyCategorizedListItemResponse(
            FullyCategorizedProduct product
    ) {
        return new FindFullyCategorizedProductListItemRestResponse(
                product.id(),
                product.name(),
                product.onlineStatus(),
                product.longDescription(),
                product.shortDescription(),
                product.categoryIds(),
                product.categoryName()
        );
    }

    private FindProductListItemRestResponse toListItemResponse(Product product) {
        return new FindProductListItemRestResponse(
                product.id().value(),
                product.name().value(),
                product.onlineStatus().name(),
                product.longDescription().value(),
                product.shortDescription().value(),
                product.categoryIds().stream()
                        .map(CategoryId::value)
                        .collect(Collectors.toSet())
        );
    }

}
