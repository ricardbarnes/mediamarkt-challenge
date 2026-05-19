package es.mediamarkt.product.application.categories;

import es.mediamarkt.product.MockedDataLoader;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@MockitoBean(types = MockedDataLoader.class)
public abstract class CategoryIT {

    @Autowired
    protected H2CategoryRepository testRepository;

    @AfterEach
    void cleanup() {
        testRepository.deleteAll();
    }


}
