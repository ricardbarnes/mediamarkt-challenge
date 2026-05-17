package es.mediamarkt.product.infrastructure.controller.rest.categories.update;

public record UpdateCategoryRestRequest(
        String name,
        Long catalogId
) {
}
