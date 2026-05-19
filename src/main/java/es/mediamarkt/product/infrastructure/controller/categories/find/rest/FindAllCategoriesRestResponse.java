package es.mediamarkt.product.infrastructure.controller.categories.find.rest;

import java.util.List;

public record FindAllCategoriesRestResponse(
        List<FindCategoryListItemRestResponse> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
