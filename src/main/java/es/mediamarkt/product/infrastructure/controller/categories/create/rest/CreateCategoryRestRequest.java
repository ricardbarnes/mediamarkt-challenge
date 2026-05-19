package es.mediamarkt.product.infrastructure.controller.categories.create.rest;

public record CreateCategoryRestRequest(
        String name,
        Long catalogId
) {
}
