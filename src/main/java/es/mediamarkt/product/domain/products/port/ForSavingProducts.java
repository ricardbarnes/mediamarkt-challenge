package es.mediamarkt.product.domain.products.port;

import es.mediamarkt.product.domain.products.model.Product;

public interface ForSavingProducts {

    void save(Product aggregate);

}
