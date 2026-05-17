package es.mediamarkt.shared.domain.pagination;

import java.util.List;

public record PagedResult<T>(
        List<T> data,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {

    public static <T> PagedResult<T> of(
            List<T> data,
            int currentPage,
            int pageSize,
            long totalElements,
            int totalPages
    ) {
        return new PagedResult<>(
                data,
                currentPage,
                pageSize,
                totalElements,
                totalPages
        );
    }

    public static <T> PagedResult<T> of(List<T> data, PageRequest pageRequest, long totalElements) {
        var totalPages = pageRequest.size() > 0
                ? (int) ((totalElements + pageRequest.size() - 1) / pageRequest.size())
                : 0;
        return of(data, pageRequest.page(), pageRequest.size(), totalElements, totalPages);
    }

}
