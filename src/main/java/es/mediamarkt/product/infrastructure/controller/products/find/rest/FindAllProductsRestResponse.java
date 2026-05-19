package es.mediamarkt.product.infrastructure.controller.products.find.rest;

import java.util.List;

public record FindAllProductsRestResponse(
        List<FindProductListItemRestResponse> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
