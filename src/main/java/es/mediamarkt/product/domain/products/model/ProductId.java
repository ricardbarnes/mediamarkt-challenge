package es.mediamarkt.product.domain.products.model;

import es.mediamarkt.shared.domain.valueobject.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class ProductId extends Id {

    public static ProductId of(Long value) {
        return new ProductId(value);
    }

    private ProductId(Long aValue) {
        super(aValue);
    }

}
