package es.mediamarkt.product.infrastructure.controller.products.create.rest;

import es.mediamarkt.product.application.products.create.ProductCreator;
import es.mediamarkt.product.domain.products.model.ProductLongDescription;
import es.mediamarkt.product.domain.products.model.ProductName;
import es.mediamarkt.product.domain.products.model.ProductOnlineStatus;
import es.mediamarkt.product.domain.products.model.ProductShortDescription;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to products")
public class CreateProductRestController {

    private final ProductCreator creator;
    private final CreateProductRestMapper mapper;

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with name, descriptions, online status, and associated categories."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Product successfully created",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateProductRestResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request payload"
    )
    public ResponseEntity<CreateProductRestResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product creation request payload",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateProductRestRequest.class)
                    )
            )
            @RequestBody CreateProductRestRequest request
    ) {

        var categoryIds = request.categoryIds().stream()
                .map(CategoryId::of)
                .collect(Collectors.toSet());

        var product = creator.create(
                ProductName.of(request.name()),
                ProductOnlineStatus.valueOf(request.onlineStatus()),
                ProductLongDescription.of(request.longDescription()),
                ProductShortDescription.of(request.shortDescription()),
                categoryIds
        );

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id().value())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(product));
    }

}