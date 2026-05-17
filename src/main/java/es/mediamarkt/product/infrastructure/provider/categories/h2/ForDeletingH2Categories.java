package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.categories.port.ForDeletingCategories;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForDeletingH2Categories implements ForDeletingCategories {

    private final H2CategoryRepository repository;

    @Override
    public void deleteById(CategoryId value) {
        if (!repository.existsById(value.value())) {
            throw CategoryNotFoundError.becauseOf(value);
        }
        repository.deleteById(value.value());
    }

}
