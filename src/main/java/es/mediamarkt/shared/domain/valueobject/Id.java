package es.mediamarkt.shared.domain.valueobject;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Id {

    protected final long value;

    protected Id(Long value) {
        guardNull(value);
        this.value = value;
    }

    private void guardNull(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    public long value() {
        return value;
    }

}
