package es.mediamarkt.product.domain.categories.port;

import es.mediamarkt.product.domain.shared.categories.model.CategoryId;

public interface ForDeletingCategories {

    void deleteById(CategoryId value);

}
