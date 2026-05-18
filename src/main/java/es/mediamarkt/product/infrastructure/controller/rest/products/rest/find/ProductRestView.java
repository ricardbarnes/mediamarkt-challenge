package es.mediamarkt.product.infrastructure.controller.rest.products.rest.find;

public enum ProductRestView {

    DEFAULT,
    FULLY_CATEGORIZED;

    public static ProductRestView fromQueryParam(String view) {
        if (view == null || view.isBlank()) {
            return DEFAULT;
        }
        var normalized = view.toUpperCase();
        return switch (normalized) {
            case "FULLY_CATEGORIZED" -> FULLY_CATEGORIZED;
            default -> throw new IllegalArgumentException("Unknown view: " + view);
        };
    }

}
