package es.mediamarkt.product.domain.products.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductShortDescriptionMother {

    public static ProductShortDescription of(String value) {
        return ProductShortDescription.of(value != null ? value : "A short description");
    }

}
