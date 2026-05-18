package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

import java.util.Map;

public record FindFullyCategorizedProductListItemRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Map<Long, String> categories
) {
}
