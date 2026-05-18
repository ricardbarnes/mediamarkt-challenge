package es.mediamarkt.product.application.products.update;

import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.port.ForFindingProducts;
import es.mediamarkt.product.domain.products.port.ForSavingProducts;
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
public class ProductUpdaterTest {

    @Mock
    private ForFindingProducts findingMock;

    @Mock
    private ForSavingProducts savingMock;

    @InjectMocks
    private ProductUpdater sut;

    @Test
    @DisplayName("it should update an existing product")
    void update_shouldUpdate() {
        // given
        var id = ProductIdMother.of(null);
        var oldName = ProductNameMother.of("old name");
        var oldOnlineStatus = ProductOnlineStatus.ACTIVE;
        var oldLongDescription = ProductLongDescriptionMother.of("old long desc");
        var oldShortDescription = ProductShortDescriptionMother.of("old short desc");
        var oldCategoryIds = Set.of(CategoryIdMother.of(1L));
        var existing = ProductMother.create(
                id,
                oldName,
                oldOnlineStatus,
                oldLongDescription,
                oldShortDescription,
                oldCategoryIds
        );
        var newName = ProductNameMother.of("new name");
        var newOnlineStatus = ProductOnlineStatus.BLOCKED;
        var newLongDescription = ProductLongDescriptionMother.of("new long desc");
        var newShortDescription = ProductShortDescriptionMother.of("new short desc");
        var newCategoryIds = Set.of(CategoryIdMother.of(2L));
        var updated = ProductMother.create(
                id,
                newName,
                newOnlineStatus,
                newLongDescription,
                newShortDescription,
                newCategoryIds
        );
        var error = ProductNotFoundError.becauseOf(id);
        Mockito.when(findingMock.findById(id))
                .thenReturn(existing);

        // when
        var actual = sut.update(
                id,
                newName,
                newOnlineStatus,
                newLongDescription,
                newShortDescription,
                newCategoryIds
        );

        // then
        Assertions.assertThat(actual).isEqualTo(updated);

        Mockito.verify(findingMock).findById(id);
        Mockito.verify(savingMock).save(Mockito.any(Product.class));
    }

    @Test
    @DisplayName("it should fail when a product ID doesn't exist")
    void update_shouldFail_whenIdNotExists() {
        // given
        var id = ProductIdMother.of(null);
        var newName = ProductNameMother.of("new name");
        var newOnlineStatus = ProductOnlineStatus.ACTIVE;
        var newLongDescription = ProductLongDescriptionMother.of(null);
        var newShortDescription = ProductShortDescriptionMother.of(null);
        var newCategoryIds = Set.of(CategoryIdMother.of(null));
        var error = ProductNotFoundError.becauseOf(id);
        Mockito.when(findingMock.findById(id))
                .thenThrow(error);

        // when + then
        Assertions.assertThatThrownBy(() -> sut.update(
                        id,
                        newName,
                        newOnlineStatus,
                        newLongDescription,
                        newShortDescription,
                        newCategoryIds
                ))
                .isEqualTo(error);

        Mockito.verify(findingMock).findById(id);
        Mockito.verifyNoInteractions(savingMock);
    }

}