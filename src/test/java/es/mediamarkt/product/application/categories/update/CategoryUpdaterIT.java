package es.mediamarkt.product.application.categories.update;

import es.mediamarkt.product.application.IT;
import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.categories.model.CategoryMother;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryUpdaterIT extends IT {

    @Autowired
    private CategoryUpdater sut;

    @Test
    @DisplayName("it should update a category")
    void update_shouldUpdate() {
        // given
        var existing = CategoryMother.create(null, null, null);
        testRepository.save(
                H2Category.builder()
                        .withId(existing.id().value())
                        .withName(existing.name().value())
                        .withCatalogId(existing.catalogId().value())
                        .build()
        );
        var newName = CategoryName.of("Updated category");
        var newCatalogId = CatalogId.of(999L);

        // when
        var result = sut.update(existing.id(), newName, newCatalogId);

        // then
        var persisted = testRepository.findById(existing.id().value());
        Assertions.assertThat(persisted).isPresent();
        var entity = persisted.get();
        Assertions.assertThat(entity.getName())
                .isEqualTo(newName.value());
        Assertions.assertThat(entity.getCatalogId())
                .isEqualTo(newCatalogId.value());
        Assertions.assertThat(result.name().value())
                .isEqualTo(newName.value());
        Assertions.assertThat(result.catalogId().value())
                .isEqualTo(newCatalogId.value());
    }

    @Test
    @DisplayName("it should fail when category doesn't exist")
    void update_shouldFail_whenIdDoesNotExist() {
        // given
        var id = CategoryMother.create(null, null, null).id();
        var newName = CategoryName.of("Updated category");
        var newCatalogId = CatalogId.of(999L);

        // when + then
        Assertions.assertThatThrownBy(() ->
                sut.update(id, newName, newCatalogId)
        ).isInstanceOf(CategoryNotFoundError.class);
        Assertions.assertThat(testRepository.findAll()).isEmpty();
    }

}