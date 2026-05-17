package es.mediamarkt.product.infrastructure.provider.categories.h2;

import es.mediamarkt.product.domain.categories.error.CategoryNotFoundError;
import es.mediamarkt.product.domain.categories.model.Category;
import es.mediamarkt.product.domain.categories.port.ForFindingCategories;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import es.mediamarkt.shared.domain.pagination.Paginated;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForFindingH2Categories implements ForFindingCategories {

    private final H2CategoryRepository repository;

    private final H2CategoryMapper mapper;

    @Override
    public Paginated<Category> findAllPaginated(PageRequest pageRequest) {
        var pageable = Pageable.ofSize(pageRequest.size()).withPage(pageRequest.page());
        var page = repository.findAll(pageable);
        var data = page.getContent().stream()
                .map(mapper::toDomain)
                .toList();
        return Paginated.of(data, pageRequest, page.getTotalElements());
    }

    @Override
    public Category findById(CategoryId value) {
        return repository.findById(value.value())
                .map(mapper::toDomain)
                .orElseThrow(() -> CategoryNotFoundError.becauseOf(value));
    }

}
