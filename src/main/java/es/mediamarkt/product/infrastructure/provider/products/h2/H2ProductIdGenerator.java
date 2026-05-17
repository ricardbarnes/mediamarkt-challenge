package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2ProductIdGenerator implements ProductIdGenerator {

    private final H2ProductRepository repository;

    @Override
    public Long generate() {
        return repository.getNextSequenceValue();
    }

}
