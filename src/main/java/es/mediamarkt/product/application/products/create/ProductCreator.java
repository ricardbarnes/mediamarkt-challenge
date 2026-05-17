package es.mediamarkt.product.application.products.create;

import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.port.ForSavingProducts;
import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductCreator {

    private final ProductIdGenerator idGenerator;

    private final ForSavingProducts saving;

    public Product create(
            ProductName aName,
            ProductOnlineStatus anOnlineStatus,
            ProductLongDescription aLongDescription,
            ProductShortDescription aShortDescription,
            Set<CategoryId> aCategoryIds
    ) {
        var id = idGenerator.generate();
        var aggregate = Product.create(id, aName, anOnlineStatus, aLongDescription, aShortDescription, aCategoryIds);
        saving.save(aggregate);
        return aggregate;
    }

}
