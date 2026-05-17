package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.find;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.shared.domain.pagination.PagedResult;
import org.springframework.stereotype.Component;

@Component
public final class FindCategoryRestMapper {

    public FindCategoryByIdRestResponse toFindByIdResponse(Category category) {
        return new FindCategoryByIdRestResponse(
                category.id().value(),
                category.name().value(),
                category.catalogId().value()
        );
    }

    public FindAllCategoriesRestResponse toFindAllResponse(PagedResult<Category> pagedResult) {
        var data = pagedResult.data().stream()
                .map(this::toListItemResponse)
                .toList();
        return new FindAllCategoriesRestResponse(
                data,
                pagedResult.currentPage(),
                pagedResult.pageSize(),
                pagedResult.totalElements(),
                pagedResult.totalPages()
        );
    }

    private FindCategoryListItemRestResponse toListItemResponse(Category category) {
        return new FindCategoryListItemRestResponse(
                category.id().value(),
                category.name().value(),
                category.catalogId().value()
        );
    }

}
