package es.mediamarkt.product.application.products.find;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.port.ForFindingProducts;
import es.mediamarkt.product.domain.products.port.ProductViewRepository;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.Paginated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFinder {

    private final ForFindingProducts finding;

    private final ProductViewRepository viewRepository;

    public Paginated<Product> findAllPaginated(PageRequest pageRequest) {
        return finding.findAllPaginated(pageRequest);
    }

    public Product findById(ProductId value) {
        return finding.findById(value);
    }

    public FullyCategorizedProduct findFullyCategorizedById(ProductId value) {
        return viewRepository.findFullyCategorizedById(value);
    }

    public Paginated<FullyCategorizedProduct> findAllFullyCategorizedPaginated(PageRequest pageRequest) {
        return viewRepository.findFullyCategorizedPaginated(pageRequest);
    }

}
