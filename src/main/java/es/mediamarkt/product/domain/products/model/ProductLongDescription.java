package es.mediamarkt.product.domain.products.model;

import es.mediamarkt.shared.domain.valueobject.StringVo;

public final class ProductLongDescription extends StringVo {

    public static ProductLongDescription of(String value) {
        return new ProductLongDescription(value);
    }

    private ProductLongDescription(String value) {
        super(value);
    }

}
