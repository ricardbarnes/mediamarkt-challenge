package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.update;

public record UpdateCategoryRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
