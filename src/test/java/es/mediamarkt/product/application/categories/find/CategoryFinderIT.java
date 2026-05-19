package es.mediamarkt.product.application.categories.find;

import es.mediamarkt.product.application.IT;
import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.categories.model.CategoryMother;
import es.mediamarkt.product.infrastructure.provider.categories.h2.H2Category;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryFinderIT extends IT {

    @Autowired
    private CategoryFinder sut;

    @Test
    @DisplayName("it should find all categories paginated")
    void findAllPaginated_shouldFindAllPaginated() {
        // given
        var expected = CategoryMother.create(null, null, null);
        var testEntity = testRepository.save(
                H2Category.builder()
                        .withId(expected.id().value())
                        .withName(expected.name().value())
                        .withCatalogId(expected.catalogId().value())
                        .build()
        );
        var pageRequest = PageRequest.of(0, 10);

        // when
        var result = sut.findAllPaginated(pageRequest);

        // then
        Assertions.assertThat(result.data()).isNotEmpty();
        Assertions.assertThat(result.data().getFirst().id().value())
                .isEqualTo(testEntity.getId());
    }

    @Test
    @DisplayName("it should find a category by its ID")
    void findById_shouldFindCategoryById() {
        // given
        var aggregate = CategoryMother.create(null, null, null);
        var testEntity = testRepository.save(
                H2Category.builder()
                        .withId(aggregate.id().value())
                        .withName(aggregate.name().value())
                        .withCatalogId(aggregate.catalogId().value())
                        .build()
        );

        // when
        var result = sut.findById(aggregate.id());

        // then
        Assertions.assertThat(result.id().value())
                .isEqualTo(testEntity.getId());
        Assertions.assertThat(result.name().value())
                .isEqualTo(testEntity.getName());
        Assertions.assertThat(result.catalogId().value())
                .isEqualTo(testEntity.getCatalogId());
    }

    @Test
    @DisplayName("it should fail when category doesn't exist in H2 database")
    void findById_shouldFail_whenIdDoesNotExist() {
        // given
        var id = CategoryMother.create(null, null, null).id();

        // when + then
        Assertions.assertThatThrownBy(() -> sut.findById(id))
                .isInstanceOf(CategoryNotFoundError.class);
    }

}