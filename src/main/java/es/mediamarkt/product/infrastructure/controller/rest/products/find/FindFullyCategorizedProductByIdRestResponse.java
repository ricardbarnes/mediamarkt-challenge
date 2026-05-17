package es.mediamarkt.product.infrastructure.controller.rest.products.find;

import java.util.List;

public record FindFullyCategorizedProductByIdRestResponse(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        List<Long> categoryIds,
        String categoryName
) {
}
