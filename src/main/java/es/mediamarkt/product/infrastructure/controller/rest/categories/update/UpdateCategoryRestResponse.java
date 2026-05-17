package es.mediamarkt.product.infrastructure.controller.rest.categories.update;

public record UpdateCategoryRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
