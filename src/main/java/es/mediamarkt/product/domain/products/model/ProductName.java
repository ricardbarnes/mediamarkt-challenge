package es.mediamarkt.product.domain.products.model;

import es.mediamarkt.shared.domain.valueobject.StringVo;

public final class ProductName extends StringVo {

    public static ProductName of(String value) {
        return new ProductName(value);
    }

    private ProductName(String value) {
        super(value);
    }

}
