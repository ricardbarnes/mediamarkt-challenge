package es.mediamarkt.product.application.products.delete;

import es.mediamarkt.product.application.products.ProductIT;
import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import es.mediamarkt.product.infrastructure.provider.products.h2.H2Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Set;

class ProductDeleterIT extends ProductIT {

    @MockitoBean
    private ProductIdGenerator idGenerator;

    @Autowired
    private ProductDeleter sut;

    @Test
    @DisplayName("it should delete a product by its ID")
    void deleteById_shouldDeleteById() {
        // given
        var id = 1L;
        testRepository.save(
                H2Product.builder()
                        .withId(id)
                        .withName("Test Product")
                        .withOnlineStatus(ProductOnlineStatus.ACTIVE)
                        .withLongDescription("Long description")
                        .withShortDescription("Short description")
                        .withCategoryIds(Set.of(10L))
                        .build()
        );

        // when
        sut.deleteById(ProductId.of(id));

        // then
        var persisted = testRepository.findById(id);
        Assertions.assertThat(persisted).isEmpty();
    }

    @Test
    @DisplayName("it should do nothing when product doesn't exist")
    void deleteById_shouldDoNothing_whenIdNotExists() {
        // given
        var id = ProductId.of(999L);

        // when + then
        Assertions.assertThatCode(() ->
                sut.deleteById(id)
        ).doesNotThrowAnyException();
    }

}