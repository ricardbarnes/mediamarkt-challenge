package es.mediamarkt.product.application.products.create;

import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.products.error.ProductAlreadyExistsError;
import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.port.ForSavingProducts;
import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ProductCreatorTest {

    @Mock
    private ProductIdGenerator idGeneratorMock;

    @Mock
    private ForSavingProducts savingMock;

    @InjectMocks
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
        var expected = ProductMother.create(id, name, onlineStatus, longDescription, shortDescription, categoryIds);
        Mockito.when(idGeneratorMock.generate()).thenReturn(id);

        // when
        var actual = sut.create(name, onlineStatus, longDescription, shortDescription, categoryIds);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
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
        var id = ProductIdMother.of(null);
        var existing = ProductMother.create(
                id,
                name,
                onlineStatus,
                longDescription,
                shortDescription,
                categoryIds
        );
        var error = ProductAlreadyExistsError.becauseOf(name);
        Mockito.when(idGeneratorMock.generate()).thenReturn(id);
        Mockito.doThrow(error)
                .when(savingMock)
                .save(existing);

        // when + then
        Assertions.assertThatThrownBy(() ->
                        sut.create(name, onlineStatus, longDescription, shortDescription, categoryIds)
                )
                .isEqualTo(error);
    }

}
