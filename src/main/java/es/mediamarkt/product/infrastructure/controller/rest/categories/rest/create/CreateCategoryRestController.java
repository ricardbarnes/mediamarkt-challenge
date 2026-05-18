package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.create;

import es.mediamarkt.product.application.categories.create.CategoryCreator;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to product categories")
public class CreateCategoryRestController {

    private final CategoryCreator creator;
    private final CreateCategoryRestMapper mapper;

    @PostMapping
    @Operation(
            summary = "Create a new category",
            description = "Creates a new product category within a given catalog and returns the created resource."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category successfully created",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateCategoryRestResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request payload"
    )
    public ResponseEntity<CreateCategoryRestResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Category creation request payload",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateCategoryRestRequest.class)
                    )
            )
            @RequestBody CreateCategoryRestRequest request
    ) {
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