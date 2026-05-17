package es.mediamarkt.product.domain.products.port;

import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;

import java.util.List;

public interface ProductViewRepository {

    List<FullyCategorizedProduct> findAll(); // TODO: paginate

    FullyCategorizedProduct findFullyCategorizedById(ProductId value);

}
