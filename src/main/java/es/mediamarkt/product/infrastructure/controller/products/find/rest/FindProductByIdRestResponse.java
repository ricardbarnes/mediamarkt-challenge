package es.mediamarkt.product.infrastructure.controller.products.find.rest;

import java.util.Set;

public record FindProductByIdRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Set<Long> categoryIds
) {
}
