package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.Paginated;
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

    public FindAllProductsRestResponse toFindAllResponse(Paginated<Product> paginated) {
        var data = paginated.data().stream()
                .map(this::toListItemResponse)
                .toList();
        return new FindAllProductsRestResponse(
                data,
                paginated.currentPage(),
                paginated.pageSize(),
                paginated.totalElements(),
                paginated.totalPages()
        );
    }

    public FindFullyCategorizedProductByIdRestResponse toFindFullyCategorizedByIdResponse(
            FullyCategorizedProduct view
    ) {
        return new FindFullyCategorizedProductByIdRestResponse(
                view.id(),
                view.name(),
                view.onlineStatus(),
                view.longDescription(),
                view.shortDescription(),
                view.categories()
        );
    }

    public FindAllFullyCategorizedProductsRestResponse toFindAllFullyCategorizedResponse(
            Paginated<FullyCategorizedProduct> paginated
    ) {
        var data = paginated.data().stream()
                .map(this::toFullyCategorizedListItemResponse)
                .toList();
        return new FindAllFullyCategorizedProductsRestResponse(
                data,
                paginated.currentPage(),
                paginated.pageSize(),
                paginated.totalElements(),
                paginated.totalPages()
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
                product.categories()
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
