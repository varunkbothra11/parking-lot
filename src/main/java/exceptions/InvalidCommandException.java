package exceptions;

/**
 * @author varun.bothra
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
