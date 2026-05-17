package es.mediamarkt.product.domain.products.port;

import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.PagedResult;

public interface ProductViewRepository {

    PagedResult<FullyCategorizedProduct> findFullyCategorizedPaginated(PageRequest pageRequest);

    FullyCategorizedProduct findFullyCategorizedById(ProductId value);

}
