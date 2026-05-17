package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.find;

public record FindCategoryListItemRestResponse(
        Long id,
        String name,
        Long catalogId
) {
}
