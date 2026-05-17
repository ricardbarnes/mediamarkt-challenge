package es.mediamarkt.product.infrastructure.controller.rest.products.rest.update;

import java.util.Set;

public record UpdateProductRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Set<Long> categoryIds
) {
}
