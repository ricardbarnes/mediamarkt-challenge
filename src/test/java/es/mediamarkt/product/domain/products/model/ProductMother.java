package es.mediamarkt.product.domain.products.model;

import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class ProductMother {

    public static Product create(
            ProductId anId,
            ProductName aName,
            ProductOnlineStatus anOnlineStatus,
            ProductLongDescription aLongDescription,
            ProductShortDescription aShortDescription,
            Set<CategoryId> aCategoryIdSet
    ) {
        return Product.create(
                anId != null ? anId : ProductId.of(10L),
                aName != null ? aName : ProductName.of("AWESOME PRODUCT NAME"),
                anOnlineStatus != null ? anOnlineStatus : ProductOnlineStatus.ACTIVE,
                aLongDescription != null ? aLongDescription : ProductLongDescription.of("A super long description"),
                aShortDescription != null ? aShortDescription : ProductShortDescription.of("A short description"),
                aCategoryIdSet != null ? aCategoryIdSet : Set.of()
        );
    }

}
