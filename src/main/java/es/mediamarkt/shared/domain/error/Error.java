package es.mediamarkt.shared.domain.error;

import lombok.EqualsAndHashCode;

/**
 * Domain error (exception) base class.
 */
@EqualsAndHashCode(callSuper = true)
public abstract class Error extends RuntimeException {

    protected Error(String message) {
        super(message);
    }

}
