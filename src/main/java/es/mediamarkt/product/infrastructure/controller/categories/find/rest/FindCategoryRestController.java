package es.mediamarkt.product.infrastructure.controller.categories.find.rest;

import es.mediamarkt.product.application.categories.find.CategoryFinder;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to product categories")
public class FindCategoryRestController {

    private final CategoryFinder finder;
    private final FindCategoryRestMapper mapper;

    @GetMapping
    @Operation(
            summary = "Get all categories (paginated)",
            description = "Returns a paginated list of categories belonging to the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Categories successfully retrieved"
    )
    public FindAllCategoriesRestResponse findAll(
            @Parameter(
                    description = "Page index (0-based)",
                    example = "0"
            )
            @RequestParam(defaultValue = "0") int page,

            @Parameter(
                    description = "Number of items per page",
                    example = "20"
            )
            @RequestParam(defaultValue = "20") int size
    ) {
        var pageRequest = PageRequest.of(page, size);
        return mapper.toFindAllResponse(finder.findAllPaginated(pageRequest));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get category by ID",
            description = "Retrieves a category using its unique identifier."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category successfully retrieved"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found"
    )
    public FindCategoryByIdRestResponse findById(
            @Parameter(
                    description = "Unique identifier of the category",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        return mapper.toFindByIdResponse(finder.findById(CategoryId.of(id)));
    }

}