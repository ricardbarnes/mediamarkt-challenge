package es.mediamarkt.product.infrastructure.controller.rest.products.rest.update;

import es.mediamarkt.product.application.products.update.ProductUpdater;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.model.ProductLongDescription;
import es.mediamarkt.product.domain.products.model.ProductName;
import es.mediamarkt.product.domain.products.model.ProductOnlineStatus;
import es.mediamarkt.product.domain.products.model.ProductShortDescription;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to products")
public class UpdateProductRestController {

    private final ProductUpdater updater;
    private final UpdateProductRestMapper mapper;

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a product",
            description = "Updates an existing product including name, descriptions, online status, and category assignments."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product successfully updated",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateProductRestResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request payload"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found"
    )
    public UpdateProductRestResponse update(
            @Parameter(
                    description = "Unique identifier of the product",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product update payload",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UpdateProductRestRequest.class)
                    )
            )
            @RequestBody UpdateProductRestRequest request
    ) {

        var categoryIds = request.categoryIds().stream()
                .map(CategoryId::of)
                .collect(Collectors.toSet());

        var product = updater.update(
                ProductId.of(id),
                ProductName.of(request.name()),
                ProductOnlineStatus.valueOf(request.onlineStatus()),
                ProductLongDescription.of(request.longDescription()),
                ProductShortDescription.of(request.shortDescription()),
                categoryIds
        );

        return mapper.toResponse(product);
    }

}