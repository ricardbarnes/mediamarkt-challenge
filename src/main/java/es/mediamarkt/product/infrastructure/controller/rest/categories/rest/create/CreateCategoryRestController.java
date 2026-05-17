package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.create;

import es.mediamarkt.product.application.categories.create.CategoryCreator;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CreateCategoryRestController {

    private final CategoryCreator creator;

    private final CreateCategoryRestMapper mapper;

    @PostMapping
    public ResponseEntity<CreateCategoryRestResponse> create(@RequestBody CreateCategoryRestRequest request) {
        var category = creator.create(
                CategoryName.of(request.name()),
                CatalogId.of(request.catalogId())
        );
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id().value())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(category));
    }

}
