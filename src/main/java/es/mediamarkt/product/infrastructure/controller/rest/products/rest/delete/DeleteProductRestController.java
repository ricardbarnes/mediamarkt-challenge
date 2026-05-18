package es.mediamarkt.product.infrastructure.controller.rest.products.rest.delete;

import es.mediamarkt.product.application.products.delete.ProductDeleter;
import es.mediamarkt.product.domain.products.model.ProductId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to products")
public class DeleteProductRestController {

    private final ProductDeleter deleter;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a product",
            description = "Deletes an existing product by its unique identifier. This operation cannot be undone."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Product successfully deleted"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found"
    )
    public void delete(
            @Parameter(
                    description = "Unique identifier of the product to delete",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        deleter.deleteById(ProductId.of(id));
    }

}