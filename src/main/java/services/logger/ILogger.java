package services.logger;

/**
 * @author varun.bothra
 */
public interface ILogger {
    void info(String message);

    void error(String message, Exception exception);

    void error(Exception exception);
}
