package es.mediamarkt.product.infrastructure.controller.products.find.rest;

import java.util.Map;

public record FindFullyCategorizedProductByIdRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Map<Long, String> categories
) {
}
