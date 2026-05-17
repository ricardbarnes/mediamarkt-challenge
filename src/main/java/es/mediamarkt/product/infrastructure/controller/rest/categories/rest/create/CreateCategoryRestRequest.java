package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.create;

public record CreateCategoryRestRequest(
        String name,
        Long catalogId
) {
}
