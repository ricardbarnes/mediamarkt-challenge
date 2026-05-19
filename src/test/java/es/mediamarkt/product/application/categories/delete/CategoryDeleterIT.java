package es.mediamarkt.product.application.categories.delete;

import es.mediamarkt.product.application.IT;
import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryDeleterIT extends IT {

    @Autowired
    private CategoryDeleter sut;

    @Test
    @DisplayName("it should delete by ID")
    void deleteById_shouldDelete() {
        // given
        var id = CategoryIdMother.of(null);
        testRepository.save(
                H2Category.builder()
                        .withId(id.value())
                        .withName("temp")
                        .withCatalogId(1L)
                        .build()
        );
        Assertions.assertThat(testRepository.findById(id.value())).isPresent();

        // when
        sut.deleteById(id);

        // then
        Assertions.assertThat(testRepository.findById(id.value())).isNotPresent();
    }

    @Test
    @DisplayName("it should do nothing if category doesn't exist")
    void deleteById_shouldDoNothing_whenIdNotExists() {
        // given
        var id = CategoryIdMother.of(null);
        Assertions.assertThat(testRepository.findById(id.value())).isNotPresent();

        // when
        sut.deleteById(id);

        // then
        Assertions.assertThat(testRepository.findById(id.value())).isNotPresent();
    }

}