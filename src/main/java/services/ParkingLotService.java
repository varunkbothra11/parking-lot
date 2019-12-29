package services;

import beans.ParkingLot;
import enums.Color;
import enums.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandInputException;
import services.logger.ILogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class ParkingLotService {
    private ILogger log;
    private ParkingLot parkingLot;

    public ParkingLotService(ILogger log) {
        this.log = log;
        parkingLot = ParkingLot.getInstance(log);
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
            String absoluteFilePath = getAbsoluteFilePath(filePath);
            List<String> fileContents = readFromFile(absoluteFilePath);
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
            throw new InvalidCommandException("Invalid command");
        }

        switch (command) {
            case CREATE_PARKING_LOT:
                if (inputs.length == 0) {
                    throw new InvalidCommandInputException("Please specify the number of slots to create");
                }

                int numberOfSlots = Integer.parseInt(inputs[0]);
                return parkingLot.createParkingLot(numberOfSlots);

            case PARK:
                if (inputs.length < 2) {
                    throw new InvalidCommandInputException("Please specify the car license number and the color");
                }

                String licenseNumber = inputs[0];
                Color color = Color.getCommandEnum(inputs[1]);
                return parkingLot.parkVehicle(licenseNumber, color);
            case EXIT:
                System.exit(0);

            default:
                throw new InvalidCommandException("Invalid command");
        }
    }

    private String processCommand(String line) throws Exception {
        final String[] words = line.trim().split(" ");
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

    private List<String> readFromFile(String absoluteFilePath) throws FileNotFoundException {
        List<String> fileContents = new ArrayList<String>();
        File file = new File(absoluteFilePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            fileContents.add(scanner.nextLine());
        }

        return fileContents;
    }

    private String getAbsoluteFilePath(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("Invalid file path");
        }

        return file.getAbsolutePath();
    }

    private void logResults(List<String> results) {
        for (String result : results) {
            log.info(result);
        }
    }
}
