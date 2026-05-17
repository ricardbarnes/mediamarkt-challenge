package es.mediamarkt.product.domain.shared.catalogs.model;

import es.mediamarkt.shared.domain.valueobject.StringVo;

public final class CatalogName extends StringVo {

    public static CatalogName of(String value) {
        return new CatalogName(value);
    }

    private CatalogName(String value) {
        super(value);
    }

}
