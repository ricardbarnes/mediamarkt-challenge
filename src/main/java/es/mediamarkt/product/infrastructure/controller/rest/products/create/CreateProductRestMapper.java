package es.mediamarkt.product.infrastructure.controller.rest.products.create;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class CreateProductRestMapper {

    public CreateProductRestResponse toResponse(Product product) {
        return new CreateProductRestResponse(
                product.id().value(),
                product.name().value(),
                product.onlineStatus().name(),
                product.longDescription().value(),
                product.shortDescription().value(),
                product.categoryIds().stream()
                        .map(CategoryId::value)
                        .collect(Collectors.toSet())
        );
    }

}
