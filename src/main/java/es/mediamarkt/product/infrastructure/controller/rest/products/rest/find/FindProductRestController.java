package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

import es.mediamarkt.product.application.products.find.ProductFinder;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class FindProductRestController {

    private final ProductFinder finder;

    private final FindProductRestMapper mapper;

    @GetMapping
    public Object findAll(
            @RequestParam(required = false) String view,
            @RequestParam(defaultValue = "0") int page,
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
    public Object findById(
            @PathVariable Long id,
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
