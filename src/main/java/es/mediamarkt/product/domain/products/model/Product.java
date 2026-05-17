package es.mediamarkt.product.domain.products.model;

import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.aggregate.AggregateRoot;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
public final class Product extends AggregateRoot {

    public static Product create(
            ProductId anId,
            ProductName aName,
            ProductOnlineStatus anOnlineStatus,
            ProductLongDescription aLongDescription,
            ProductShortDescription aShortDescription,
            Set<CategoryId> aCategoryIdSet
    ) {
        return new Product(
                anId,
                aName,
                anOnlineStatus,
                aLongDescription,
                aShortDescription,
                aCategoryIdSet
        );
    }

    public static Product rehydrate(
            ProductId anId,
            ProductName aName,
            ProductOnlineStatus anOnlineStatus,
            ProductLongDescription aLongDescription,
            ProductShortDescription aShortDescription,
            Set<CategoryId> aCategoryIds
    ) {
        return new Product(
                anId,
                aName,
                anOnlineStatus,
                aLongDescription,
                aShortDescription,
                aCategoryIds
        );
    }

    private final ProductId id;

    private ProductName name;

    private ProductOnlineStatus onlineStatus;

    private ProductLongDescription longDescription;

    private ProductShortDescription shortDescription;

    private Set<CategoryId> categoryIds;

    private Product(
            ProductId anId,
            ProductName aName,
            ProductOnlineStatus anOnlineStatus,
            ProductLongDescription aLongDescription,
            ProductShortDescription aShortDescription,
            Set<CategoryId> aCategoryIdSet
    ) {
        id = anId;
        name = aName;
        onlineStatus = anOnlineStatus;
        longDescription = aLongDescription;
        shortDescription = aShortDescription;
        categoryIds = aCategoryIdSet;
    }

    public void updateName(ProductName newName) {
        name = newName;
    }

    public void updateOnlineStatus(ProductOnlineStatus newOnlineStatus) {
        onlineStatus = newOnlineStatus;
    }

    public void updateLongDescription(ProductLongDescription newLongDescription) {
        longDescription = newLongDescription;
    }

    public void updateShortDescription(ProductShortDescription newShortDescription) {
        shortDescription = newShortDescription;
    }

    public void updateCategoryIds(Set<CategoryId> newCategoryIds) {
        categoryIds = newCategoryIds;
    }

    public ProductId id() {
        return id;
    }

    public ProductName name() {
        return name;
    }

    public ProductOnlineStatus onlineStatus() {
        return onlineStatus;
    }

    public ProductLongDescription longDescription() {
        return longDescription;
    }

    public ProductShortDescription shortDescription() {
        return shortDescription;
    }

    public Set<CategoryId> categoryIds() {
        return categoryIds;
    }

}
