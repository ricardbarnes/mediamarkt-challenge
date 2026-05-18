package es.mediamarkt.product.application.categories.delete;

import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.categories.port.ForDeletingCategories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryDeleterTest {

    @Mock
    private ForDeletingCategories deletingMock;

    @InjectMocks
    private CategoryDeleter sut;

    @Test
    @DisplayName("it should delete by ID")
    void deleteById_shouldDelete() {
        // given
        var id = CategoryIdMother.of(null);

        // when
        sut.deleteById(id);

        // then
        Mockito.verify(deletingMock).deleteById(id);
        Mockito.verifyNoMoreInteractions(deletingMock);
    }

    @Test
    @DisplayName("it should do nothing if a category doesn't exist")
    void deleteById_shouldDoNothing_whenIdNotExists() {
        // given
        var id = CategoryIdMother.of(null);

        // when
        sut.deleteById(id);

        // then
        Mockito.verify(deletingMock).deleteById(id);
    }

}
