package services.logger;

/**
 * @author varun.bothra
 */
public class ConsoleLogger implements ILogger {

    public void info(String message) {
        System.out.println(message);
    }

    public void error(String message, Exception exception) {
        System.out.println(message);
        exception.printStackTrace();
    }

    @Override
    public void error(Exception exception) {
        exception.printStackTrace();
    }
}
