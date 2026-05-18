package es.mediamarkt.product.application.categories.update;

import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.categories.model.CategoryMother;
import es.mediamarkt.product.domain.categories.model.CategoryNameMother;
import es.mediamarkt.product.domain.categories.port.ForFindingCategories;
import es.mediamarkt.product.domain.categories.port.ForSavingCategories;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogIdMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryUpdaterTest {

    @Mock
    private ForFindingCategories findingMock;

    @Mock
    private ForSavingCategories savingMock;

    @InjectMocks
    private CategoryUpdater sut;

    @Test
    @DisplayName("it should update a category")
    public void update_shouldUpdate() {
        // given
        var id = CategoryIdMother.of(null);
        var existingName = CategoryNameMother.of("Existing Name");
        var existingCatalogId = CatalogIdMother.of(1L);
        var existing = CategoryMother.create(id, existingName, existingCatalogId);
        var newName = CategoryNameMother.of("Updated category");
        var newCatalogId = CatalogIdMother.of(2L);
        var updated = CategoryMother.create(id, newName, newCatalogId);
        Mockito.when(findingMock.findById(id)).thenReturn(existing);

        // when
        var actual = sut.update(id, newName, newCatalogId);

        // then
        Mockito.verify(findingMock).findById(id);
        Mockito.verify(savingMock).save(updated);
        Assertions.assertThat(actual).isEqualTo(updated);
    }

    @Test
    @DisplayName("it should fail when a category doesn't exist")
    public void update_shouldFail_whenIdDoesNotExist() {
        // given
        var id = CategoryIdMother.of(null);
        var newName = CategoryNameMother.of("Updated category");
        var error = CategoryNotFoundError.becauseOf(id);
        var catalogId = CatalogIdMother.of(null);
        Mockito.when(findingMock.findById(id)).thenThrow(error);

        // when + then
        Assertions.assertThatThrownBy(() -> sut.update(id, newName, catalogId))
                .isEqualTo(error);

        Mockito.verify(findingMock).findById(id);
        Mockito.verifyNoInteractions(savingMock);
    }

}