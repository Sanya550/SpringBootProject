package overonix.overo.exception;

public class NotAllowsCoordinateException extends Exception {
    public NotAllowsCoordinateException() {
        super("Your latitude or longitude is wrong.");
    }
}
