package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.service.CategoryIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2CategoryIdGenerator implements CategoryIdGenerator {

    private final H2CategoryRepository repository;

    @Override
    public Long generate() {
        return repository.getNextSequenceValue();
    }

}
