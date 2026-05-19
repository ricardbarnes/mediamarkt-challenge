package es.mediamarkt.product.infrastructure.controller.products.update.rest;

import java.util.Set;

public record UpdateProductRestRequest(
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Set<Long> categoryIds
) {
}
