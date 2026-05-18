package es.mediamarkt.product.domain.categories.model;

import es.mediamarkt.shared.domain.valueobject.StringVo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class CategoryName extends StringVo {

    public static CategoryName of(String value) {
        return new CategoryName(value);
    }

    private CategoryName(String value) {
        super(value);
    }

}
