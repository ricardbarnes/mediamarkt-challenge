package es.mediamarkt.product.infrastructure.controller.products.create.rest;

import java.util.Set;

public record CreateProductRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Set<Long> categoryIds
) {
}
