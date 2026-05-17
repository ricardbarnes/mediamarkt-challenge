package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.service.CategoryIdGenerator;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class H2CategoryIdGenerator implements CategoryIdGenerator {

    private final AtomicLong counter = new AtomicLong(1000000L);

    @Override
    public CategoryId generate() {
        return CategoryId.of(counter.incrementAndGet());
    }

}
