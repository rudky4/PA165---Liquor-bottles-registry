package cz.muni.fi.pa165.exceptions;

/**
 * @author mhajas
 */
public class BottleIsAlreadyMarkedAsToxic extends RuntimeException {

    public BottleIsAlreadyMarkedAsToxic() {
        super();
    }

    public BottleIsAlreadyMarkedAsToxic(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BottleIsAlreadyMarkedAsToxic(String message, Throwable cause) {
        super(message, cause);
    }

    public BottleIsAlreadyMarkedAsToxic(String message) {
        super(message);
    }

    public BottleIsAlreadyMarkedAsToxic(Throwable cause) {
        super(cause);
    }
}
