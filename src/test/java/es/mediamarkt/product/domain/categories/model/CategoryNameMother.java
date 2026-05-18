package es.mediamarkt.product.domain.categories.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryNameMother {

    public static CategoryName of(String value) {
        return CategoryName.of(value != null ? value : "My awesome category");
    }

}
