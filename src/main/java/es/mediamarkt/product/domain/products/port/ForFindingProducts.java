package es.mediamarkt.product.domain.products.port;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.Paginated;

public interface ForFindingProducts {

    Paginated<Product> findAllPaginated(PageRequest pageRequest);

    Product findById(ProductId value);

}
