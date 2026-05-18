package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.delete;

import es.mediamarkt.product.application.categories.delete.CategoryDeleter;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to product categories")
public class DeleteCategoryRestController {

    private final CategoryDeleter deleter;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a category",
            description = "Deletes an existing category by its unique identifier. This operation is irreversible."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Category successfully deleted"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found"
    )
    public void delete(
            @Parameter(
                    description = "Unique identifier of the category to delete",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        deleter.deleteById(CategoryId.of(id));
    }

}