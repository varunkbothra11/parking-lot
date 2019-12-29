package exceptions;

public class InvalidCommandInputException extends Exception {
    public InvalidCommandInputException(String message) {
        super(message);
    }
}
