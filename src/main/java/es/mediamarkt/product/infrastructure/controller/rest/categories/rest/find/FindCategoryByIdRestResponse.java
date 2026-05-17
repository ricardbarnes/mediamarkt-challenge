package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.find;

public record FindCategoryByIdRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
