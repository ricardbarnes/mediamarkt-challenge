package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.create;

public record CreateCategoryRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
