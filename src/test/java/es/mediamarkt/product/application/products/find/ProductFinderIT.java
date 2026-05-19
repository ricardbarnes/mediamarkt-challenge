package es.mediamarkt.product.application.products.find;

import es.mediamarkt.product.application.products.ProductIT;
import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.ProductIdMother;
import es.mediamarkt.product.infrastructure.provider.products.h2.H2Product;
import es.mediamarkt.product.infrastructure.provider.products.h2.H2ProductRepository;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.valueobject.Id;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

class ProductFinderIT extends ProductIT {

    @Autowired
    private ProductFinder sut;

    @Autowired
    private H2ProductRepository testRepository;

    @Test
    @DisplayName("it should find all products paginated")
    void findAllPaginated_shouldFindAllPaginated() {
        // given
        var expected = testRepository.save(
                H2Product.builder()
                        .withId(1L)
                        .withName("TV")
                        .withOnlineStatus(null)
                        .withLongDescription("long")
                        .withShortDescription("short")
                        .withCategoryIds(Set.of(10L))
                        .build()
        );
        var pageRequest = PageRequest.of(0, 10);

        // when
        var result = sut.findAllPaginated(pageRequest);

        // then
        Assertions.assertThat(result.data()).isNotEmpty();
        var actual = result.data().getFirst();
        Assertions.assertThat(actual.id().value())
                .isEqualTo(expected.getId());
        Assertions.assertThat(actual.name().value())
                .isEqualTo(expected.getName());
        Assertions.assertThat(actual.onlineStatus())
                .isEqualTo(expected.getOnlineStatus());
        Assertions.assertThat(actual.shortDescription().value())
                .isEqualTo(expected.getShortDescription());
        Assertions.assertThat(
                actual.categoryIds().stream()
                        .map(Id::value)
                        .collect(java.util.stream.Collectors.toSet())
        ).isEqualTo(expected.getCategoryIds());
    }

    @Test
    @DisplayName("it should find a product by its ID")
    void findById_shouldFindById() {
        // given
        var expected = testRepository.save(
                H2Product.builder()
                        .withId(2L)
                        .withName("Audio")
                        .withOnlineStatus(null)
                        .withLongDescription("long")
                        .withShortDescription("short")
                        .withCategoryIds(Set.of(20L))
                        .build()
        );

        // when
        var actual = sut.findById(ProductIdMother.of(expected.getId()));

        // then
        Assertions.assertThat(actual.id().value())
                .isEqualTo(expected.getId());
        Assertions.assertThat(actual.name().value())
                .isEqualTo(expected.getName());
        Assertions.assertThat(actual.onlineStatus())
                .isEqualTo(expected.getOnlineStatus());
        Assertions.assertThat(actual.longDescription().value())
                .isEqualTo(expected.getLongDescription());
        Assertions.assertThat(actual.shortDescription().value())
                .isEqualTo(expected.getShortDescription());
        Assertions.assertThat(
                actual.categoryIds().stream()
                        .map(Id::value)
                        .collect(java.util.stream.Collectors.toSet())
        ).isEqualTo(expected.getCategoryIds());
    }

    @Test
    @DisplayName("it should fail when product doesn't exist")
    void findById_shouldFail_whenIdNotExists() {
        // given
        var id = ProductIdMother.of(999L);

        // when + then
        Assertions.assertThatThrownBy(() -> sut.findById(id))
                .isInstanceOf(ProductNotFoundError.class);
    }

}