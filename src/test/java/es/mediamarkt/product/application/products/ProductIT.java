package es.mediamarkt.product.application.products;

import es.mediamarkt.product.MockedDataLoader;
import es.mediamarkt.product.infrastructure.provider.products.h2.H2ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@MockitoBean(types = MockedDataLoader.class)
public abstract class ProductIT {

    @Autowired
    protected H2ProductRepository testRepository;

    @AfterEach
    void cleanup() {
        testRepository.deleteAll();
    }


}
