package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.error.ProductAlreadyExistsError;
import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.port.ForSavingProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForSavingH2Products implements ForSavingProducts {

    private final H2ProductRepository repository;

    private final H2ProductMapper mapper;

    @Override
    public void save(Product aggregate) {
        try {
            repository.save(mapper.toInfra(aggregate));
        } catch (DataIntegrityViolationException e) {
            throw ProductAlreadyExistsError.becauseOf(aggregate.name());
        }
    }

}
