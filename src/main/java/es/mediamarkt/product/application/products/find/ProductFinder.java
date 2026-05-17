package es.mediamarkt.product.application.products.find;

import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.port.ForFindingProducts;
import es.mediamarkt.product.domain.products.port.ProductViewRepository;
import es.mediamarkt.product.domain.products.view.FullyCategorizedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFinder {

    private final ForFindingProducts finding;

    private final ProductViewRepository viewRepository;

    public List<Product> findAll() { // TODO: paginate
        return finding.findAll();
    }

    public Product findById(ProductId value) {
        return finding.findById(value);
    }

    public FullyCategorizedProduct findFullyCategorizedById(ProductId value) {
        return viewRepository.findFullyCategorizedById(value);
    }

}
