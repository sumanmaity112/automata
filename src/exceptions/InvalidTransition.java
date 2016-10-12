package exceptions;

public class InvalidTransition extends RuntimeException {
    public InvalidTransition(String message) {
        super(message);
    }

    public InvalidTransition() {
        super();
    }
}
