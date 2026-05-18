package es.mediamarkt.product.domain.shared.catalogs.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CatalogIdMother {

    public static CatalogId of(Long value) {
        return CatalogId.of(value != null ? value : 44);

    }

}
