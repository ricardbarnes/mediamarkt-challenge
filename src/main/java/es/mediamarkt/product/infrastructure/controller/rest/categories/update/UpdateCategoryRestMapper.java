package es.mediamarkt.product.infrastructure.controller.rest.categories.update;

import es.mediamarkt.product.domain.categories.model.Category;
import org.springframework.stereotype.Component;

@Component
public final class UpdateCategoryRestMapper {

    public UpdateCategoryRestResponse toResponse(Category category) {
        return new UpdateCategoryRestResponse(
                category.id().value(),
                category.name().value(),
                category.catalogId().value()
        );
    }

}
