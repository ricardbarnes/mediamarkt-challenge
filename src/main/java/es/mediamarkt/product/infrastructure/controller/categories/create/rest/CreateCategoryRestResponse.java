package es.mediamarkt.product.infrastructure.controller.categories.create.rest;

public record CreateCategoryRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
