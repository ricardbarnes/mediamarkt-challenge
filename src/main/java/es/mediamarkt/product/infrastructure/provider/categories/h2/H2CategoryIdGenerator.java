package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.service.CategoryIdGenerator;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class H2CategoryIdGenerator implements CategoryIdGenerator {

    private final H2CategoryRepository repository;

    @Override
    @Transactional
    public CategoryId generate() {
        Long nextId = repository.getNextSequenceValue();
        return CategoryId.of(nextId);
    }

}