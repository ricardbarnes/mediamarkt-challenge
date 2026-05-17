package es.mediamarkt.product.infrastructure.controller.rest.categories.find;

public record FindCategoryByIdRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
