package es.mediamarkt.product.application.categories.find;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.port.ForFindingCategories;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFinder {

    private final ForFindingCategories finding;

    public PagedResult<Category> findAll(PageRequest pageRequest) {
        return finding.findAllPaginated(pageRequest);
    }

    public Category findById(CategoryId value) {
        return finding.findById(value);
    }

}
