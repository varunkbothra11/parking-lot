package exceptions;

/**
 * @author varun.bothra
 */
public class InvalidCommandInputException extends Exception {
    public InvalidCommandInputException(String message) {
        super(message);
    }
}
