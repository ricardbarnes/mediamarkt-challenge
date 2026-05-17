package es.mediamarkt.product.application.categories.find;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.port.ForFindingCategories;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryFinder {

    private final ForFindingCategories finding;

    public List<Category> findAll() { // TODO: paginate
        return finding.findAll();
    }

    public Category findById(CategoryId value) {
        return finding.findById(value);
    }

}
