package es.mediamarkt.product.infrastructure.controller.rest.products.update;

import java.util.Set;

public record UpdateProductRestRequest(
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Set<Long> categoryIds
) {
}
