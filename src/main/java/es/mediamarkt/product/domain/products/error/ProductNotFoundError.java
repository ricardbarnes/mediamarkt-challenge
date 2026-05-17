package es.mediamarkt.product.domain.products.error;

import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.shared.domain.error.Error;

public final class ProductNotFoundError extends Error {

    public static ProductNotFoundError becauseOf(ProductId value) {
        return new ProductNotFoundError(aMessageFor(value));
    }

    private static String aMessageFor(ProductId value) {
        return "Product ID %s not found.".formatted(value);
    }

    private ProductNotFoundError(String message) {
        super(message);
    }

}
