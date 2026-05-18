package es.mediamarkt.product.application.categories.find;

import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.categories.model.CategoryIdMother;
import es.mediamarkt.product.domain.categories.model.CategoryMother;
import es.mediamarkt.product.domain.categories.port.ForFindingCategories;
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
public class CategoryFinderTest {

    @Mock
    private ForFindingCategories findingMock;

    @InjectMocks
    private CategoryFinder finder;

    @Test
    @DisplayName("it should find all categories paginated")
    void findAllPaginated_shouldFindAllPaginated() {
        // given
        var category = CategoryMother.create(null, null, null);
        var aggregates = List.of(category);
        var pageRequest = PageRequest.of(0, 10);
        var expected = Paginated.of(aggregates, pageRequest, 10);
        Mockito.when(findingMock.findAllPaginated(Mockito.eq(pageRequest))).thenReturn(expected);

        // when
        var actual = finder.findAllPaginated(pageRequest);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("it should find a category by its ID")
    void findById_shouldFindCategoryById() {
        // given
        var id = CategoryIdMother.of(null);
        var expected = CategoryMother.create(id, null, null);
        Mockito.when(findingMock.findById(id)).thenReturn(expected);

        // when
        var actual = finder.findById(id);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("it should fail when a category doesn't exist")
    void findById_shouldFail_whenIdDoesNotExist() {
        // given
        var id = CategoryIdMother.of(null);
        var error = CategoryNotFoundError.becauseOf(id);
        Mockito.when(findingMock.findById(id)).thenThrow(error);

        // when + then
        Assertions.assertThatThrownBy(() -> finder.findById(id))
                .isEqualTo(error);
    }

}