package es.mediamarkt.product.infrastructure.controller.rest.categories.update;

import es.mediamarkt.product.application.categories.update.CategoryUpdater;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class UpdateCategoryRestController {

    private final CategoryUpdater categoryUpdater;

    private final UpdateCategoryRestMapper mapper;

    @PutMapping("/{id}")
    public UpdateCategoryRestResponse update(
            @PathVariable Long id,
            @RequestBody UpdateCategoryRestRequest request
    ) {
        var category = categoryUpdater.update(
                CategoryId.of(id),
                CategoryName.of(request.name()),
                CatalogId.of(request.catalogId())
        );
        return mapper.toResponse(category);
    }

}
