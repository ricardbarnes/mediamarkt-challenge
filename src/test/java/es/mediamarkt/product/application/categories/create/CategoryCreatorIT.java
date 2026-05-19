package es.mediamarkt.product.application.categories.create;

import es.mediamarkt.product.application.IT;
import es.mediamarkt.product.domain.categories.error.CategoryAlreadyExistsError;
import es.mediamarkt.product.domain.categories.model.CategoryNameMother;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogIdMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryCreatorIT extends IT {

    @Autowired
    private CategoryCreator sut;

    @Test
    @DisplayName("it should create a category in H2 database")
    void create_shouldCreate() {
        // given
        var name = CategoryNameMother.of(null);
        var catalogId = CatalogIdMother.of(null);

        // when
        var actual = sut.create(name, catalogId);

        // then
        var persisted = testRepository.findById(actual.id().value());
        Assertions.assertThat(persisted).isPresent();
        var entity = persisted.get();
        Assertions.assertThat(entity.getId())
                .isEqualTo(actual.id().value());
        Assertions.assertThat(entity.getName())
                .isEqualTo(name.value());
        Assertions.assertThat(entity.getCatalogId())
                .isEqualTo(catalogId.value());
    }

    @Test
    @DisplayName("it should fail when category already exists in the same catalog using H2 database")
    void create_shouldFail_whenCategoryAlreadyExists() {
        // given
        var name = CategoryNameMother.of(null);
        var catalogId = CatalogIdMother.of(null);
        sut.create(name, catalogId);

        // when + then
        Assertions.assertThatThrownBy(() ->
                sut.create(name, catalogId)
        ).isInstanceOf(CategoryAlreadyExistsError.class);
    }

}