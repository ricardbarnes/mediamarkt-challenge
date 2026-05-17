package es.mediamarkt.product.application.categories.create;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.categories.port.ForSavingCategories;
import es.mediamarkt.product.domain.categories.service.CategoryIdGenerator;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreator {

    private final CategoryIdGenerator idGenerator;

    private final ForSavingCategories saving;

    public Category create(
            CategoryName aName,
            CatalogId aCatalogId
    ) {
        var id = CategoryId.of(idGenerator.generate());
        var aggregate = Category.create(id, aName, aCatalogId);
        saving.save(aggregate);
        return aggregate;
    }

}
