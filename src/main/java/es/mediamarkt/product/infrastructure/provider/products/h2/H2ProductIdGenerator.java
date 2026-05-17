package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class H2ProductIdGenerator implements ProductIdGenerator {

    private final AtomicLong counter = new AtomicLong(1000000L);

    @Override
    public ProductId generate() {
        return ProductId.of(counter.incrementAndGet());
    }

}
