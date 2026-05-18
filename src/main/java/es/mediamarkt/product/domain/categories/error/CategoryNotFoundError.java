package es.mediamarkt.product.domain.categories.error;

import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.error.Error;

public final class CategoryNotFoundError extends Error {

    public static CategoryNotFoundError becauseOf(CategoryId value) {
        return new CategoryNotFoundError(aMessageFor(value));
    }

    private static String aMessageFor(CategoryId id) {
        return "Category ID %s not found.".formatted(id.value());
    }

    private CategoryNotFoundError(String message) {
        super(message);
    }

}
