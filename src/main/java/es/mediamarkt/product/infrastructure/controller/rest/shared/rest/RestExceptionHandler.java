package es.mediamarkt.product.infrastructure.controller.rest.shared.rest;

import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.infrastructure.controller.rest.categories.rest.create.CreateCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.rest.categories.rest.delete.DeleteCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.rest.categories.rest.find.FindCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.rest.categories.rest.update.UpdateCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.rest.products.rest.create.CreateProductRestController;
import es.mediamarkt.product.infrastructure.controller.rest.products.rest.delete.DeleteProductRestController;
import es.mediamarkt.product.infrastructure.controller.rest.products.rest.find.FindProductRestController;
import es.mediamarkt.product.infrastructure.controller.rest.products.rest.update.UpdateProductRestController;
import es.mediamarkt.shared.domain.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {
        CreateCategoryRestController.class,
        FindCategoryRestController.class,
        UpdateCategoryRestController.class,
        DeleteCategoryRestController.class,
        CreateProductRestController.class,
        FindProductRestController.class,
        UpdateProductRestController.class,
        DeleteProductRestController.class
})
public class RestExceptionHandler {

    @SuppressWarnings("unused")
    @ExceptionHandler({
            CategoryNotFoundError.class,
            ProductNotFoundError.class
    })
    public ResponseEntity<RestErrorResponse> handleNotFoundError(Error error) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorResponse(error.getMessage()));
    }

}
