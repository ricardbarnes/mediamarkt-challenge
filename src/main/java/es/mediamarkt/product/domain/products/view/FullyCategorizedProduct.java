package es.mediamarkt.product.domain.products.view;

import java.util.List;

public record FullyCategorizedProduct(
        Long id,
        String name,
        String onlineStatus,
        String longDescription,
        String shortDescription,
        List<Long> categoryIds,
        String categoryName
) {
}
