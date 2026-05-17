package es.mediamarkt.product.application.products.delete;

import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.port.ForDeletingProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleter {

    private final ForDeletingProducts deleting;

    public void deleteById(ProductId value) {
        deleting.deleteById(value);
    }

}
