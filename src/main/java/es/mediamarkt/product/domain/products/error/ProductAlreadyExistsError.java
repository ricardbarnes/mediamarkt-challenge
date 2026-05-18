package es.mediamarkt.product.domain.products.error;

import es.mediamarkt.product.domain.products.model.ProductName;
import es.mediamarkt.shared.domain.error.Error;

public final class ProductAlreadyExistsError extends Error {

    public static ProductAlreadyExistsError becauseOf(ProductName value) {
        return new ProductAlreadyExistsError(aMessageFor(value));
    }

    private static String aMessageFor(ProductName name) {
        return "Product name '%s' already exists.".formatted(name.value());
    }

    private ProductAlreadyExistsError(String message) {
        super(message);
    }

}
