package services.logger;

/**
 * @author varun.bothra
 */
public class BlackHoleLogger implements ILogger {
    public void info(String message) {

    }

    public void error(String message, Exception exception) {

    }

    @Override
    public void error(Exception exception) {

    }
}
