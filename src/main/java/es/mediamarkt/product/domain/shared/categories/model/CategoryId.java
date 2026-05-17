package es.mediamarkt.product.domain.shared.categories.model;

import es.mediamarkt.shared.domain.valueobject.Id;

public final class CategoryId extends Id {

    public static CategoryId of(Long value) {
        return new CategoryId(value);
    }

    private CategoryId(Long aValue) {
        super(aValue);
    }

}
