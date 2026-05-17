package es.mediamarkt.product.infrastructure.controller.rest.products.rest.delete;

import es.mediamarkt.product.application.products.delete.ProductDeleter;
import es.mediamarkt.product.domain.products.model.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class DeleteProductRestController {

    private final ProductDeleter deleter;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleter.deleteById(ProductId.of(id));
    }

}
