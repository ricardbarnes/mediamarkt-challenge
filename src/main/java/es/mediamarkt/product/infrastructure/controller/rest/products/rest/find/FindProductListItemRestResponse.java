package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

import java.util.Set;

public record FindProductListItemRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Set<Long> categoryIds
) {
}
