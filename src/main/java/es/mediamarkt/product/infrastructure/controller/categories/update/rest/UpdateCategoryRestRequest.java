package es.mediamarkt.product.infrastructure.controller.categories.update.rest;

public record UpdateCategoryRestRequest(
        String name,
        Long catalogId
) {
}
