package es.mediamarkt.product.domain.products.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductIdMother {

    public static ProductId of(Long value) {
        return ProductId.of(value != null ? value : 10);
    }

}
