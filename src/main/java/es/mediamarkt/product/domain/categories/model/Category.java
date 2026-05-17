package es.mediamarkt.product.domain.categories.model;

import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.aggregate.AggregateRoot;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Category extends AggregateRoot {

    public static Category create(
            CategoryId anId,
            CategoryName aName,
            CatalogId aCatalogId
    ) {
        return new Category(
                anId,
                aName,
                aCatalogId
        );
    }

    public static Category rehydrate(
            CategoryId anId,
            CategoryName aName,
            CatalogId aCatalogId
    ) {
        return new Category(
                anId,
                aName,
                aCatalogId
        );
    }

    private final CategoryId id;

    private CategoryName name;

    private CatalogId catalogId;

    private Category(
            CategoryId anId,
            CategoryName aName,
            CatalogId aCatalogId
    ) {
        id = anId;
        name = aName;
        catalogId = aCatalogId;
    }

    public void updateName(CategoryName newName) {
        name = newName;
    }

    public void updateCatalogId(CatalogId newCatalogId) {
        catalogId = newCatalogId;
    }

    public CategoryId id() {
        return id;
    }

    public CategoryName name() {
        return name;
    }

    public CatalogId catalogId() {
        return catalogId;
    }

}
