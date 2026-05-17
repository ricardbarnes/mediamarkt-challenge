package es.mediamarkt.product.infrastructure.controller.rest.categories.find;

import java.util.List;

public record FindAllCategoriesRestResponse(
        List<FindCategoryListItemRestResponse> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
