package es.mediamarkt.product.domain.categories.port;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.PagedResult;

public interface ForFindingCategories {

    PagedResult<Category> findAllPaginated(PageRequest pageRequest);

    Category findById(CategoryId value);

}
