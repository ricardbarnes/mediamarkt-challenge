package es.mediamarkt.product.infrastructure.controller.rest.categories.rest.find;

import es.mediamarkt.product.application.categories.find.CategoryFinder;
import es.mediamarkt.product.domain.shared.categories.model.CategoryId;
import es.mediamarkt.shared.domain.pagination.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class FindCategoryRestController {

    private final CategoryFinder finder;

    private final FindCategoryRestMapper mapper;

    @GetMapping
    public FindAllCategoriesRestResponse findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        var pageRequest = PageRequest.of(page, size);
        return mapper.toFindAllResponse(finder.findAllPaginated(pageRequest));
    }

    @GetMapping("/{id}")
    public FindCategoryByIdRestResponse findById(@PathVariable Long id) {
        return mapper.toFindByIdResponse(finder.findById(CategoryId.of(id)));
    }

}
