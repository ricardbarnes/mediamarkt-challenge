package es.mediamarkt.product.infrastructure.controller.shared.rest;

import es.mediamarkt.product.domain.categories.error.CategoryAlreadyExistsError;
import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.products.error.ProductAlreadyExistsError;
import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.infrastructure.controller.categories.create.rest.CreateCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.categories.delete.rest.DeleteCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.categories.find.rest.FindCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.categories.update.rest.UpdateCategoryRestController;
import es.mediamarkt.product.infrastructure.controller.products.create.rest.CreateProductRestController;
import es.mediamarkt.product.infrastructure.controller.products.delete.rest.DeleteProductRestController;
import es.mediamarkt.product.infrastructure.controller.products.find.rest.FindProductRestController;
import es.mediamarkt.product.infrastructure.controller.products.update.rest.UpdateProductRestController;
import es.mediamarkt.shared.domain.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(
        assignableTypes = {
                CreateCategoryRestController.class,
                FindCategoryRestController.class,
                UpdateCategoryRestController.class,
                DeleteCategoryRestController.class,
                CreateProductRestController.class,
                FindProductRestController.class,
                UpdateProductRestController.class,
                DeleteProductRestController.class
        }
)
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

    @SuppressWarnings("unused")
    @ExceptionHandler({
            CategoryAlreadyExistsError.class,
            ProductAlreadyExistsError.class
    })
    public ResponseEntity<RestErrorResponse> handleAlreadyExistsError(Error error) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new RestErrorResponse(error.getMessage()));
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
    })
    public ResponseEntity<RestErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorResponse(exception.getMessage()));
    }

}
