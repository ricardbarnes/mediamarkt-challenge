package es.mediamarkt.product.domain.products.model;

import es.mediamarkt.shared.domain.valueobject.StringVo;

public final class ProductShortDescription extends StringVo {

    public static ProductShortDescription of(String value) {
        return new ProductShortDescription(value);
    }

    private ProductShortDescription(String value) {
        super(value);
    }

}
