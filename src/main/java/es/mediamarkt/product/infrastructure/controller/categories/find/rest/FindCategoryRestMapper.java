package es.mediamarkt.product.infrastructure.controller.categories.find.rest;

import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.shared.domain.pagination.Paginated;
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

    public FindAllCategoriesRestResponse toFindAllResponse(Paginated<Category> paginated) {
        var data = paginated.data().stream()
                .map(this::toListItemResponse)
                .toList();
        return new FindAllCategoriesRestResponse(
                data,
                paginated.currentPage(),
                paginated.pageSize(),
                paginated.totalElements(),
                paginated.totalPages()
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
