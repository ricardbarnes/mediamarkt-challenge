package es.mediamarkt.product.domain.categories.error;

import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.shared.domain.error.Error;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class CategoryAlreadyExistsError extends Error {

    public static CategoryAlreadyExistsError becauseOf(CategoryName name, CatalogId catalogId) {
        return new CategoryAlreadyExistsError(aMessageFor(name, catalogId));
    }

    private static String aMessageFor(CategoryName name, CatalogId catalogId) {
        return "Category name '%s' with catalog ID %s already exists.".formatted(name.value(), catalogId.value());
    }

    private CategoryAlreadyExistsError(String message) {
        super(message);
    }

}
