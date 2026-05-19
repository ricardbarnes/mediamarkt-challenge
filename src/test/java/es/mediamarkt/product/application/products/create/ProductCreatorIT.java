package es.mediamarkt.product.application.products.create;

import es.mediamarkt.product.application.products.ProductIT;
import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.products.error.ProductAlreadyExistsError;
import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Set;

class ProductCreatorIT extends ProductIT {

    @MockitoBean
    private ProductIdGenerator idGenerator;

    @Autowired
    private ProductCreator sut;

    @Test
    @DisplayName("it should create a product")
    void create_shouldCreate() {
        // given
        var name = ProductNameMother.of(null);
        var onlineStatus = ProductOnlineStatus.ACTIVE;
        var longDescription = ProductLongDescriptionMother.of(null);
        var shortDescription = ProductShortDescriptionMother.of(null);
        var categoryIds = Set.of(CategoryIdMother.of(null));
        var id = ProductIdMother.of(null);
        Mockito.when(idGenerator.generate()).thenReturn(id);

        // when
        var actual = sut.create(
                name,
                onlineStatus,
                longDescription,
                shortDescription,
                categoryIds
        );

        // then
        var persisted = testRepository.findById(actual.id().value());
        Assertions.assertThat(persisted).isPresent();
        var entity = persisted.get();
        Assertions.assertThat(entity.getId())
                .isEqualTo(actual.id().value());
        Assertions.assertThat(entity.getName())
                .isEqualTo(name.value());
        Assertions.assertThat(entity.getOnlineStatus())
                .isEqualTo(onlineStatus);
        Assertions.assertThat(entity.getLongDescription())
                .isEqualTo(longDescription.value());
        Assertions.assertThat(entity.getShortDescription())
                .isEqualTo(shortDescription.value());
    }

    @Test
    @DisplayName("it should fail when the product name and the category IDs combination already exist")
    void create_shouldFail_whenNameAndCategoryIdsExist() {
        // given
        var name = ProductNameMother.of(null);
        var onlineStatus = ProductOnlineStatus.ACTIVE;
        var longDescription = ProductLongDescriptionMother.of(null);
        var shortDescription = ProductShortDescriptionMother.of(null);
        var categoryIds = Set.of(CategoryIdMother.of(null));
        var id1 = ProductIdMother.of(1L);
        var id2 = ProductIdMother.of(2L);
        Mockito.when(idGenerator.generate())
                .thenReturn(id1)
                .thenReturn(id2);
        sut.create(
                name,
                onlineStatus,
                longDescription,
                shortDescription,
                categoryIds
        );

        // when + then
        Assertions.assertThatThrownBy(() ->
                sut.create(
                        name,
                        onlineStatus,
                        longDescription,
                        shortDescription,
                        categoryIds
                )
        ).isInstanceOf(ProductAlreadyExistsError.class);
    }

}