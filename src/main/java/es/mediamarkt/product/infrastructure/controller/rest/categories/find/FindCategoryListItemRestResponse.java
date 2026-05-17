package es.mediamarkt.product.infrastructure.controller.rest.categories.find;

public record FindCategoryListItemRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
