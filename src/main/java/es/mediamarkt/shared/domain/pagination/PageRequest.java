package es.mediamarkt.shared.domain.pagination;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class PageRequest {

    private final int page;
    private final int size;

    private PageRequest(int aPage, int aSize) {
        guardNegativeIndex(aPage);
        guardBadSize(aSize);
        page = aPage;
        size = aSize;
    }

    private void guardNegativeIndex(int aPage) {
        if (aPage < 0) {
            throw new IllegalArgumentException("Page index cannot be negative");
        }
    }

    private void guardBadSize(int aSize) {
        if (aSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }
    }

    public static PageRequest of(int aPage, int aSize) {
        return new PageRequest(aPage, aSize);
    }

    public int page() {
        return this.page;
    }

    public int size() {
        return this.size;
    }

    public int offset() {
        return this.page * this.size;
    }

}