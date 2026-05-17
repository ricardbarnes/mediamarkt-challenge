package es.mediamarkt.product.infrastructure.controller.rest.products.find;

import java.util.List;

public record FindAllFullyCategorizedProductsRestResponse(
        List<FindFullyCategorizedProductListItemRestResponse> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
