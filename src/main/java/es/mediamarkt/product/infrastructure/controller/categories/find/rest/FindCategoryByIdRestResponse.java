package es.mediamarkt.product.infrastructure.controller.categories.find.rest;

public record FindCategoryByIdRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
