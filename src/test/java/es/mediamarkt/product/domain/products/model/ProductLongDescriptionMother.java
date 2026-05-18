package es.mediamarkt.product.domain.products.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductLongDescriptionMother {

    public static ProductLongDescription of(String value) {
        return ProductLongDescription.of(value != null ? value : "A super long product description");
    }

}
