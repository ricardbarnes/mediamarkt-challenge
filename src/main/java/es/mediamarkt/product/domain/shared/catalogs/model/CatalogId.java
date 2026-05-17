package es.mediamarkt.product.domain.shared.catalogs.model;

import es.mediamarkt.shared.domain.valueobject.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class CatalogId extends Id {

    public static CatalogId of(Long value) {
        return new CatalogId(value);
    }

    private CatalogId(Long aValue) {
        super(aValue);
    }

}
