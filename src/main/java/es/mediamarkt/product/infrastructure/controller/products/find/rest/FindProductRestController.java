package es.mediamarkt.product.infrastructure.controller.products.find.rest;

import es.mediamarkt.product.application.products.find.ProductFinder;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to products")
public class FindProductRestController {

    private final ProductFinder finder;
    private final FindProductRestMapper mapper;

    @GetMapping
    @Operation(
            summary = "Get all products",
            description = """
                    Returns a paginated list of products.
                    
                    Supports a view mode:
                    - FULLY_CATEGORIZED: includes category details
                    """
    )
    @ApiResponse(responseCode = "200", description = "Products successfully retrieved")
    public Object findAll(
            @Parameter(
                    description = "View mode: none | FULLY_CATEGORIZED",
                    example = "FULLY_CATEGORIZED"
            )
            @RequestParam(required = false) String view,

            @Parameter(description = "Page index (0-based)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        var pageRequest = PageRequest.of(page, size);

        return switch (ProductRestView.fromQueryParam(view)) {
            case DEFAULT -> mapper.toFindAllResponse(finder.findAllPaginated(pageRequest));
            case FULLY_CATEGORIZED -> mapper.toFindAllFullyCategorizedResponse(
                    finder.findAllFullyCategorizedPaginated(pageRequest)
            );
        };
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = """
                    Retrieves a product by its ID.
                    
                    Supports different view modes:
                    - DEFAULT: basic product information
                    - FULLY_CATEGORIZED: includes full category details
                    """
    )
    @ApiResponse(responseCode = "200", description = "Product successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public Object findById(
            @Parameter(description = "Product ID", example = "1", required = true)
            @PathVariable Long id,

            @Parameter(
                    description = "View mode: DEFAULT | FULLY_CATEGORIZED",
                    example = "DEFAULT"
            )
            @RequestParam(required = false) String view
    ) {
        var productId = ProductId.of(id);

        return switch (ProductRestView.fromQueryParam(view)) {
            case DEFAULT -> mapper.toFindByIdResponse(finder.findById(productId));
            case FULLY_CATEGORIZED -> mapper.toFindFullyCategorizedByIdResponse(
                    finder.findFullyCategorizedById(productId)
            );
        };
    }

}