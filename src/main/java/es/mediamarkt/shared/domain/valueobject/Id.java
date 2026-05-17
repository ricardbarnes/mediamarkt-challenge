package es.mediamarkt.shared.domain.valueobject;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Id {

    protected final Long value;

    protected Id(Long aValue) {
        value = aValue;
    }

}
