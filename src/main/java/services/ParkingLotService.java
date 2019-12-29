package services;

import beans.ParkingLot;
import constants.ErrorMessages;
import enums.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandInputException;
import helpers.FileProcessingHelper;
import helpers.ParkingLotHelper;
import services.logger.ILogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author varun.bothra
 */
public final class ParkingLotService {
    private ILogger log;
    private ParkingLot parkingLot;
    private ParkingLotHelper parkingLotHelper;
    private FileProcessingHelper fileProcessingHelper;

    public ParkingLotService(ILogger log) {
        this.log = log;
        parkingLot = ParkingLot.getInstance(log);
        parkingLotHelper = new ParkingLotHelper(parkingLot);
        fileProcessingHelper = new FileProcessingHelper();
    }

    public void executeFileAndLogOutput(String filePath) {
        List<String> results = executeFile(filePath);
        parkingLot = ParkingLot.getInstance(log);
        logResults(results);
    }

    public void processCommandAndLogOutput(String line) {
        try {
            log.info(processCommand(line));
        } catch (Exception exception) {
            log.error(exception);
        }
    }

    public List<String> executeFile(String filePath) {
        try {
            String absoluteFilePath = fileProcessingHelper.getAbsoluteFilePath(filePath);
            List<String> fileContents = fileProcessingHelper.readFromFile(absoluteFilePath);
            return processCommands(fileContents);
        } catch (FileNotFoundException exception) {
            log.error(filePath + " is not found", exception);
        } catch (InvalidCommandException exception) {
            log.error(exception);
        } catch (InvalidCommandInputException exception) {
            log.error(exception);
        } catch (Exception exception) {
            log.error(exception);
        }

        return new ArrayList<>();
    }

    public String executeCommand(Command command, String... inputs) throws InvalidCommandInputException, InvalidCommandException {
        if (command == null) {
            throw new InvalidCommandException(ErrorMessages.INVALID_COMMAND);
        }

        switch (command) {
            case CREATE_PARKING_LOT:
                return parkingLotHelper.createParkingLot(inputs);

            case PARK:
                return parkingLotHelper.parkVehicle(inputs);

            case LEAVE:
                return parkingLotHelper.unParkVehicle(inputs);

            case STATUS:
                return parkingLotHelper.getStatus();

            case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                return parkingLotHelper.getVehicleRegistrationNumbers(inputs);

            case EXIT:
                System.exit(0);

            default:
                throw new InvalidCommandException(ErrorMessages.INVALID_COMMAND);
        }
    }

    private String processCommand(String line) throws Exception {
        String[] words = line.trim().split(" +");
        Command command = Command.getCommandEnum(words[0]);
        return executeCommand(command, Arrays.copyOfRange(words, 1, words.length));
    }

    private List<String> processCommands(List<String> lines) throws Exception {
        List<String> results = new ArrayList<String>();

        for (String line : lines) {
            processCommand(line);
        }

        return results;
    }

    private void logResults(List<String> results) {
        for (String result : results) {
            log.info(result);
        }
    }
}
