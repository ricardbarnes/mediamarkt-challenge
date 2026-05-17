package es.mediamarkt.product.infrastructure.controller.rest.categories.create;

public record CreateCategoryRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
