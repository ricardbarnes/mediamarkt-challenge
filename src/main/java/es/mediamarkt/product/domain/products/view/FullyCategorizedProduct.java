package es.mediamarkt.product.domain.products.view;

import java.util.Map;

public record FullyCategorizedProduct(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        Map<Long, String> categories
) {
}