package es.mediamarkt.product.infrastructure.controller.rest.products.create;

import es.mediamarkt.product.application.products.create.ProductCreator;
import es.mediamarkt.product.domain.products.model.ProductLongDescription;
import es.mediamarkt.product.domain.products.model.ProductName;
import es.mediamarkt.product.domain.products.model.ProductOnlineStatus;
import es.mediamarkt.product.domain.products.model.ProductShortDescription;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class CreateProductRestController {

    private final ProductCreator creator;

    private final CreateProductRestMapper mapper;

    @PostMapping
    public ResponseEntity<CreateProductRestResponse> create(@RequestBody CreateProductRestRequest request) {
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
