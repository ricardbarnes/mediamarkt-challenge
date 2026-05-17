package es.mediamarkt.product.application.categories.update;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.categories.port.ForFindingCategories;
import es.mediamarkt.product.domain.categories.port.ForSavingCategories;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdater {

    private final ForFindingCategories finding;

    private final ForSavingCategories saving;

    public Category update(
            CategoryId anId,
            CategoryName name,
            CatalogId catalogId
    ) {
        var aggregate = finding.findById(anId);
        aggregate.updateName(name);
        aggregate.updateCatalogId(catalogId);
        saving.save(aggregate);
        return aggregate;
    }

}
