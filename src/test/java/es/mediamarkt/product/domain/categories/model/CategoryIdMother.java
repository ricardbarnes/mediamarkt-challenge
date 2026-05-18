package es.mediamarkt.product.domain.categories.model;

import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryIdMother {

    public static CategoryId of(Long value) {
        return CategoryId.of(value != null ? value : 22);
    }

}
