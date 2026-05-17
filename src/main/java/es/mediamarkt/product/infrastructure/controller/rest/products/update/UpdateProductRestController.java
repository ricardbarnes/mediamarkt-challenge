package es.mediamarkt.product.infrastructure.controller.rest.products.update;

import es.mediamarkt.product.application.products.update.ProductUpdater;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.model.ProductLongDescription;
import es.mediamarkt.product.domain.products.model.ProductName;
import es.mediamarkt.product.domain.products.model.ProductOnlineStatus;
import es.mediamarkt.product.domain.products.model.ProductShortDescription;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class UpdateProductRestController {

    private final ProductUpdater updater;

    private final UpdateProductRestMapper mapper;

    @PutMapping("/{id}")
    public UpdateProductRestResponse update(
            @PathVariable Long id,
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
