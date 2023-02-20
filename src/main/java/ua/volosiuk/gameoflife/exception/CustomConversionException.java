package ua.volosiuk.gameoflife.exception;

import org.springframework.core.convert.ConversionException;

public class CustomConversionException extends ConversionException {

    public CustomConversionException(String message) {
        super(message);
    }

    public CustomConversionException(String message, Throwable cause) {
        super(message, cause);
    }

}
