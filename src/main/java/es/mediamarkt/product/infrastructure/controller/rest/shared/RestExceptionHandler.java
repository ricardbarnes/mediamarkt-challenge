package es.mediamarkt.product.infrastructure.controller.rest.shared;

import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.shared.domain.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            CategoryNotFoundError.class,
            ProductNotFoundError.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundError(Error error) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(error.getMessage()));
    }

}
