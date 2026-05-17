package es.mediamarkt.shared.domain.pagination;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public final class Paginated<T> {

    private final List<T> data;
    private final int currentPage;
    private final int pageSize;
    private final long totalElements;
    private final int totalPages;

    private Paginated(
            List<T> aData,
            int aCurrentPage,
            int aPageSize,
            long aTotalElements,
            int aTotalPages
    ) {
        guardNullData(aData);
        guardNegativeCurrentPage(aCurrentPage);
        guardNegativePageSize(aPageSize);
        guardNegativeTotalElements(aTotalElements);
        guardNegativeTotalPages(aTotalPages);

        data = List.copyOf(aData); // Defensively copy to maintain immutability
        currentPage = aCurrentPage;
        pageSize = aPageSize;
        totalElements = aTotalElements;
        totalPages = aTotalPages;
    }

    private void guardNullData(List<T> aData) {
        if (aData == null) {
            throw new IllegalArgumentException("Data list cannot be null");
        }
    }

    private void guardNegativeCurrentPage(int aCurrentPage) {
        if (aCurrentPage < 0) {
            throw new IllegalArgumentException("Current page index cannot be negative");
        }
    }

    private void guardNegativePageSize(int aPageSize) {
        if (aPageSize < 0) {
            throw new IllegalArgumentException("Page size cannot be negative");
        }
    }

    private void guardNegativeTotalElements(long aTotalElements) {
        if (aTotalElements < 0) {
            throw new IllegalArgumentException("Total elements count cannot be negative");
        }
    }

    private void guardNegativeTotalPages(int aTotalPages) {
        if (aTotalPages < 0) {
            throw new IllegalArgumentException("Total pages count cannot be negative");
        }
    }

    public static <T> Paginated<T> of(
            List<T> data,
            int currentPage,
            int pageSize,
            long totalElements,
            int totalPages
    ) {
        return new Paginated<>(
                data,
                currentPage,
                pageSize,
                totalElements,
                totalPages
        );
    }

    public static <T> Paginated<T> of(List<T> data, PageRequest pageRequest, long totalElements) {
        var totalPages = pageRequest.size() > 0
                ? (int) ((totalElements + pageRequest.size() - 1) / pageRequest.size())
                : 0;
        return of(data, pageRequest.page(), pageRequest.size(), totalElements, totalPages);
    }

    public List<T> data() {
        return this.data;
    }

    public int currentPage() {
        return this.currentPage;
    }

    public int pageSize() {
        return this.pageSize;
    }

    public long totalElements() {
        return this.totalElements;
    }

    public int totalPages() {
        return this.totalPages;
    }

}