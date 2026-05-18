package es.mediamarkt.product.application.products.delete;

import es.mediamarkt.product.domain.products.model.ProductIdMother;
import es.mediamarkt.product.domain.products.port.ForDeletingProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductDeleterTest {

    @Mock
    private ForDeletingProducts deleting;

    @InjectMocks
    private ProductDeleter sut;

    @Test
    @DisplayName("it should delete a product by its ID")
    void deleteById_shouldDeleteById() {
        // given
        var id = ProductIdMother.of(null);

        // when
        sut.deleteById(id);

        // then
        Mockito.verify(deleting).deleteById(id);
    }

    @Test
    @DisplayName("it should do nothing when a product doesn't exist")
    void deleteById_shouldDoNothing_whenIdNotExists() {
        // given
        var id = ProductIdMother.of(null);

        // when
        sut.deleteById(id);

        // then
        Mockito.verify(deleting).deleteById(id);
        Mockito.verifyNoMoreInteractions(deleting);
    }

}