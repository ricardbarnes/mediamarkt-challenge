package es.mediamarkt.product.application.categories.delete;

import es.mediamarkt.product.domain.categories.port.ForDeletingCategories;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDeleter {

    private final ForDeletingCategories deleting;

    public void deleteById(CategoryId value) {
        deleting.deleteById(value);
    }

}
