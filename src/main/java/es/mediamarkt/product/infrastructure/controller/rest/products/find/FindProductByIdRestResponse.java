package es.mediamarkt.product.infrastructure.controller.rest.products.find;

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
