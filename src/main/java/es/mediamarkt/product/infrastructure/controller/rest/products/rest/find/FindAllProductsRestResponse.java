package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

import java.util.List;

public record FindAllProductsRestResponse(
        List<FindProductListItemRestResponse> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
