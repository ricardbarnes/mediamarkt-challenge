package es.mediamarkt.product.domain.categories.port;

import es.mediamarkt.product.domain.categories.model.Category;

public interface ForSavingCategories {

    void save(Category aggregate);

}
