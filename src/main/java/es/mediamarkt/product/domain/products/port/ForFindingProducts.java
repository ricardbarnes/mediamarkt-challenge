package es.mediamarkt.product.domain.products.port;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;

import java.util.List;

public interface ForFindingProducts {

    List<Product> findAll(); // TODO: paginate

    Product findById(ProductId value);

}
