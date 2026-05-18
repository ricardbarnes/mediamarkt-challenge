package es.mediamarkt.product.domain.categories.model;

import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMother {

    public static Category create(
            CategoryId anId,
            CategoryName aName,
            CatalogId aCatalogId
    ) {
        return Category.create(
                anId != null ? anId : CategoryId.of(10L),
                aName != null ? aName : CategoryName.of("My awesome category"),
                aCatalogId != null ? aCatalogId : CatalogId.of(10L)
        );
    }

}
