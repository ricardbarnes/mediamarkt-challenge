package es.mediamarkt.product.domain.categories.port;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;

import java.util.List;

public interface ForFindingCategories {

    List<Category> findAll(); // TODO: paginate

    Category findById(CategoryId value);

}
