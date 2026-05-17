package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.create;

import es.mediamarkt.product.domain.categories.model.Category;
import org.springframework.stereotype.Component;

@Component
public final class CreateCategoryRestMapper {

    public CreateCategoryRestResponse toResponse(Category category) {
        return new CreateCategoryRestResponse(
                category.id().value(),
                category.name().value(),
                category.catalogId().value()
        );
    }

}
