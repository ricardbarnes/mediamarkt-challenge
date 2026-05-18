package es.mediamarkt.product.application.products.find;

import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.ProductIdMother;
import es.mediamarkt.product.domain.products.model.ProductMother;
import es.mediamarkt.product.domain.products.port.ForFindingProducts;
import es.mediamarkt.product.domain.products.port.ProductViewRepository;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.Paginated;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductFinderTest {

    @Mock
    private ForFindingProducts findingMock;

    @Mock
    private ProductViewRepository viewRepositoryMock;

    @InjectMocks
    private ProductFinder sut;

    @Test
    @DisplayName("it should find all products paginated")
    void findAllPaginated_shouldFindAllPaginated() {
        // given
        var pageRequest = PageRequest.of(0, 10);

        var product = ProductMother.create(null, null, null, null, null, null);
        var expected = Paginated.of(List.of(product), pageRequest, 10);

        Mockito.when(findingMock.findAllPaginated(pageRequest))
                .thenReturn(expected);

        // when
        var actual = sut.findAllPaginated(pageRequest);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("it should find a product by its ID")
    void findById_shouldFindById() {
        // given
        var id = ProductIdMother.of(null);
        var expected = ProductMother.create(null, null, null, null, null, null);

        Mockito.when(findingMock.findById(id))
                .thenReturn(expected);

        // when
        var actual = sut.findById(id);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("it should fail when a product ID doesn't exist")
    void findById_shouldFail_whenIdNotExists() {
        // given
        var id = ProductIdMother.of(null);
        var error = ProductNotFoundError.becauseOf(id);

        Mockito.when(findingMock.findById(id))
                .thenThrow(error);

        // when + then
        Assertions.assertThatThrownBy(() -> sut.findById(id))
                .isEqualTo(error);
    }

    @Test
    @DisplayName("it should find a fully categorized product by its ID")
    void findFullyCategorizedById_shouldFindFullyCategorizedById() {
        // given
        var id = ProductIdMother.of(null);
        var expected = Mockito.mock(FullyCategorizedProduct.class);

        Mockito.when(viewRepositoryMock.findFullyCategorizedById(id))
                .thenReturn(expected);

        // when
        var actual = sut.findFullyCategorizedById(id);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("it should fail finding a fully categorized product when the ID doesn't exist")
    void findFullyCategorizedById_shouldFail_whenIdNotExists() {
        // given
        var id = ProductIdMother.of(null);
        var error = ProductNotFoundError.becauseOf(id);

        Mockito.when(viewRepositoryMock.findFullyCategorizedById(id))
                .thenThrow(error);

        // when + then
        Assertions.assertThatThrownBy(() -> sut.findFullyCategorizedById(id))
                .isEqualTo(error);
    }

}