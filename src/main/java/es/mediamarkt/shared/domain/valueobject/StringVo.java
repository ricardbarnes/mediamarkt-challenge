package es.mediamarkt.shared.domain.valueobject;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class StringVo {

    protected final String value;

    protected StringVo(String aValue) {
        value = aValue;
    }

}
