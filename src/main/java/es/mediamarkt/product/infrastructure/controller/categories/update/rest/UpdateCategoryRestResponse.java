package es.mediamarkt.product.infrastructure.controller.categories.update.rest;

public record UpdateCategoryRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
