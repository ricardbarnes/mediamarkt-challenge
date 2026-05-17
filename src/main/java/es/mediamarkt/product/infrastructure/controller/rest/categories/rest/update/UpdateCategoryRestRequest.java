package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.update;

public record UpdateCategoryRestRequest(
        String name,
        Long catalogId
) {
}
