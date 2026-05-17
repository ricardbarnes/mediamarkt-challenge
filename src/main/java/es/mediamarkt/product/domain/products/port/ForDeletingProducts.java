package es.mediamarkt.product.domain.products.port;

import es.mediamarkt.product.domain.products.model.ProductId;

public interface ForDeletingProducts {

    void deleteById(ProductId value);

}
