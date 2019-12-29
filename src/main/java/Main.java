import services.ParkingLotService;
import services.logger.ConsoleLogger;
import services.logger.ILogger;

import java.util.Scanner;

/**
 * @author varun.bothra
 */
public class Main {
    private static ILogger log = new ConsoleLogger();
    private static ParkingLotService parkingLotService = new ParkingLotService(log);

    public static void main(String[] args) {
        ILogger log = new ConsoleLogger();

        if (args.length == 0) {
            processUserInputAndLogOutput();
        } else {
            executeFileAndLogOutput(args[0]);
        }
    }

    private static void processUserInputAndLogOutput() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            parkingLotService.processCommandAndLogOutput(scanner.nextLine());
        }
    }

    public static void executeFileAndLogOutput(String filePath) {
        parkingLotService.executeFileAndLogOutput(filePath);
    }
}
