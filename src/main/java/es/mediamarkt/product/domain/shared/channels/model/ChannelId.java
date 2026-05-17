package es.mediamarkt.product.domain.shared.channels.model;

import es.mediamarkt.shared.domain.valueobject.StringVo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class ChannelId extends StringVo {

    public static ChannelId of(String value) {
        return new ChannelId(value);
    }

    private ChannelId(String aValue) {
        super(aValue);
    }

}
