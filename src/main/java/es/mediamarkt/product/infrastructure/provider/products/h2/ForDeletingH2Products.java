package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.port.ForDeletingProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForDeletingH2Products implements ForDeletingProducts {

    private final H2ProductRepository repository;

    @Override
    public void deleteById(ProductId value) {
        if (!repository.existsById(value.value())) {
            throw ProductNotFoundError.becauseOf(value);
        }
        repository.deleteById(value.value());
    }

}
