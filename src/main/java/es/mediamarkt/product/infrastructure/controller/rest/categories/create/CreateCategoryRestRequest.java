package es.mediamarkt.product.infrastructure.controller.rest.categories.create;

public record CreateCategoryRestRequest(
        String name,
        Long catalogId
) {
}
