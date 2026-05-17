package es.mediamarkt.product.application.products.update;

import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.port.ForFindingProducts;
import es.mediamarkt.product.domain.products.port.ForSavingProducts;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductUpdater {

    private final ForFindingProducts finding;

    private final ForSavingProducts saving;

    public Product update(
            ProductId anId,
            ProductName name,
            ProductOnlineStatus onlineStatus,
            ProductLongDescription longDescription,
            ProductShortDescription shortDescription,
            Set<CategoryId> categoryIds
    ) {
        var aggregate = finding.findById(anId);
        aggregate.updateName(name);
        aggregate.updateOnlineStatus(onlineStatus);
        aggregate.updateLongDescription(longDescription);
        aggregate.updateShortDescription(shortDescription);
        aggregate.updateCategoryIds(categoryIds);
        saving.save(aggregate);
        return aggregate;
    }

}
