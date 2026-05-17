package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.error.ProductNotFoundError;
import es.mediamarkt.product.domain.products.model.Product;
import es.mediamarkt.product.domain.products.model.ProductId;
import es.mediamarkt.product.domain.products.port.ForFindingProducts;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForFindingH2Products implements ForFindingProducts {

    private final H2ProductRepository repository;

    private final H2ProductMapper mapper;

    @Override
    public PagedResult<Product> findAllPaginated(PageRequest pageRequest) {
        var pageable = Pageable.ofSize(pageRequest.size()).withPage(pageRequest.page());
        var page = repository.findAll(pageable);
        var data = page.getContent().stream()
                .map(mapper::toDomain)
                .toList();
        return PagedResult.of(data, pageRequest, page.getTotalElements());
    }

    @Override
    public Product findById(ProductId value) {
        return repository.findById(value.value())
                .map(mapper::toDomain)
                .orElseThrow(() -> ProductNotFoundError.becauseOf(value));
    }

}
