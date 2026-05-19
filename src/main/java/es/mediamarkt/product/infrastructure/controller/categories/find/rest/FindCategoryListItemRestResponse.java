package es.mediamarkt.product.infrastructure.controller.categories.find.rest;

public record FindCategoryListItemRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
