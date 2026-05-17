package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.port.ForSavingCategories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForSavingH2Categories implements ForSavingCategories {

    private final H2CategoryRepository repository;

    private final H2CategoryMapper mapper;

    @Override
    public void save(Category aggregate) {
        repository.save(mapper.toInfra(aggregate));
    }

}
