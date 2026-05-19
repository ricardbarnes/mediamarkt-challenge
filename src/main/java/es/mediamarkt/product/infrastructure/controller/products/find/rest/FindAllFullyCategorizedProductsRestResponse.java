package es.mediamarkt.product.infrastructure.controller.products.find.rest;

import java.util.List;

public record FindAllFullyCategorizedProductsRestResponse(
        List<FindFullyCategorizedProductListItemRestResponse> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
