package es.mediamarkt.product.application.categories.create;

import es.mediamarkt.product.domain.categories.error.CategoryAlreadyExistsError;
import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.categories.model.CategoryMother;
import es.mediamarkt.product.domain.categories.model.CategoryNameMother;
import es.mediamarkt.product.domain.categories.port.ForSavingCategories;
import es.mediamarkt.product.domain.categories.service.CategoryIdGenerator;
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
public class CategoryCreatorTest {

    @Mock
    private CategoryIdGenerator idGeneratorMock;

    @Mock
    private ForSavingCategories savingMock;

    @InjectMocks
    private CategoryCreator sut;

    @Test
    @DisplayName("it should create a category")
    void create_shouldCreate() {
        // given
        var name = CategoryNameMother.of(null);
        var catalogId = CatalogIdMother.of(null);
        var id = CategoryIdMother.of(null);
        var expected = CategoryMother.create(id, name, catalogId);
        Mockito.when(idGeneratorMock.generate()).thenReturn(id);

        // when
        var actual = sut.create(name, catalogId);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("it should fail when creating a category with an existing name on an existing catalog")
    void create_shouldFail_whenNameExists() {
        // given
        var existingName = CategoryNameMother.of(null);
        var existingCatalogId = CatalogIdMother.of(null);
        var id = CategoryIdMother.of(null);
        var existing = CategoryMother.create(id, existingName, existingCatalogId);
        var error = CategoryAlreadyExistsError.becauseOf(existingName, existingCatalogId);
        Mockito.when(idGeneratorMock.generate()).thenReturn(id);
        Mockito.doThrow(error).when(savingMock).save(existing);

        // when + then
        Assertions.assertThatThrownBy(() -> sut.create(existingName, existingCatalogId))
                .isEqualTo(error);
    }

}
