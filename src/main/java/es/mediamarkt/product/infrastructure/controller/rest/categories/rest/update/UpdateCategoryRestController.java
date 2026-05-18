package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.update;

import es.mediamarkt.product.application.categories.update.CategoryUpdater;
import es.mediamarkt.product.domain.categories.model.CategoryName;
import es.mediamarkt.product.domain.shared.catalogs.model.CatalogId;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to product categories")
public class UpdateCategoryRestController {

    private final CategoryUpdater categoryUpdater;
    private final UpdateCategoryRestMapper mapper;

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a category",
            description = "Updates an existing category by its ID, including name and catalog association."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category successfully updated",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateCategoryRestResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request payload"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found"
    )
    public UpdateCategoryRestResponse update(
            @Parameter(
                    description = "Unique identifier of the category to update",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Payload containing updated category data",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UpdateCategoryRestRequest.class)
                    )
            )
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