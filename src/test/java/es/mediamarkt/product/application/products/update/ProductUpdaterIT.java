package es.mediamarkt.product.application.products.update;

import es.mediamarkt.product.application.products.ProductIT;
import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.product.infrastructure.provider.products.h2.H2Product;
import es.mediamarkt.product.infrastructure.provider.products.h2.H2ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

class ProductUpdaterIT extends ProductIT {

    @Autowired
    private ProductUpdater sut;

    @Autowired
    private H2ProductRepository testRepository;

    @Test
    @DisplayName("it should update an existing product")
    void update_shouldUpdate() {
        // given
        var existing = testRepository.save(
                H2Product.builder()
                        .withId(1L)
                        .withName("old name")
                        .withOnlineStatus(ProductOnlineStatus.ACTIVE)
                        .withLongDescription("old long desc")
                        .withShortDescription("old short desc")
                        .withCategoryIds(Set.of(1L))
                        .build()
        );

        var newName = ProductNameMother.of("new name");
        var newOnlineStatus = ProductOnlineStatus.BLOCKED;
        var newLongDescription = ProductLongDescriptionMother.of("new long desc");
        var newShortDescription = ProductShortDescriptionMother.of("new short desc");
        var newCategoryIds = Set.of(CategoryIdMother.of(2L));

        // when
        var actual = sut.update(
                ProductIdMother.of(existing.getId()),
                newName,
                newOnlineStatus,
                newLongDescription,
                newShortDescription,
                newCategoryIds
        );

        // then
        Assertions.assertThat(actual.id().value()).isEqualTo(existing.getId());
        Assertions.assertThat(actual.name().value()).isEqualTo("new name");
        Assertions.assertThat(actual.onlineStatus()).isEqualTo(newOnlineStatus);
        Assertions.assertThat(actual.longDescription().value()).isEqualTo("new long desc");
        Assertions.assertThat(actual.shortDescription().value()).isEqualTo("new short desc");
        Assertions.assertThat(
                actual.categoryIds().stream()
                        .map(CategoryId::value)
                        .collect(Collectors.toSet())
        ).isEqualTo(Set.of(2L));

        var persisted = testRepository.findById(existing.getId()).orElseThrow();
        Assertions.assertThat(persisted.getName()).isEqualTo("new name");
    }

    @Test
    @DisplayName("it should fail when a product ID doesn't exist")
    void update_shouldFail_whenIdNotExists() {
        // given
        var id = ProductIdMother.of(999L);

        var newName = ProductNameMother.of("new name");
        var newOnlineStatus = ProductOnlineStatus.ACTIVE;
        var newLongDescription = ProductLongDescriptionMother.of(null);
        var newShortDescription = ProductShortDescriptionMother.of(null);
        var newCategoryIds = Set.of(CategoryIdMother.of(null));

        // when + then
        Assertions.assertThatThrownBy(() ->
                sut.update(
                        id,
                        newName,
                        newOnlineStatus,
                        newLongDescription,
                        newShortDescription,
                        newCategoryIds
                )
        ).isInstanceOf(ProductNotFoundError.class);
    }

}