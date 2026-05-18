package es.mediamarkt.product.domain.products.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductNameMother {

    public static ProductName of(String value) {
        return ProductName.of(value != null ? value : "A product name");
    }

}
