package helpers;

import beans.ParkingLot;
import constants.ErrorMessages;
import enums.Color;
import exceptions.InvalidCommandInputException;
import exceptions.ParkingLotAlreadyCreated;
import exceptions.ParkingLotDoesNotExists;

/**
 * @author varun.bothra
 */
public class ParkingLotHelper {
    private static ParkingLot parkingLot;

    public ParkingLotHelper(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String createParkingLot(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException(ErrorMessages.CREATE_PARKING_LOT_INVALID_INPUT);
        }

        int numberOfSlots = Integer.parseInt(inputs[0]);
        return parkingLot.createParkingLot(numberOfSlots);
    }

    public String parkVehicle(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length < 2) {
            throw new InvalidCommandInputException(ErrorMessages.PARK_VEHICLE_INVALID_INPUT);
        }

        String licenseNumber = inputs[0];
        Color color = Color.getCommandEnum(inputs[1]);

        if (color == null) {
            throw new InvalidCommandInputException(ErrorMessages.INVALID_COLOR);
        }

        return parkingLot.parkVehicle(licenseNumber, color);
    }

    public String unParkVehicle(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException(ErrorMessages.UNPARK_VEHICLE_INVALID_INPUT);
        }

        int slotNumber = Integer.parseInt(inputs[0]);
        return parkingLot.unParkVehicle(slotNumber);
    }

    public String getStatus() {
        return parkingLot.getStatus();
    }

    public String getVehicleRegistrationNumbers(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException(ErrorMessages.INVALID_COLOR);
        }

        Color color = Color.getCommandEnum(inputs[0]);

        if (color == null) {
            throw new InvalidCommandInputException(ErrorMessages.INVALID_COLOR);
        }

        return parkingLot.getVehicleRegistrationNumbers(color);
    }

    public String getSlotNumbers(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException(ErrorMessages.INVALID_COLOR);
        }

        Color color = Color.getCommandEnum(inputs[0]);

        if (color == null) {
            throw new InvalidCommandInputException(ErrorMessages.INVALID_COLOR);
        }

        return parkingLot.getSlotNumbers(color);
    }

    public String getSlotNumber(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException(ErrorMessages.INVALID_COLOR);
        }

        return parkingLot.getSlotNumber(inputs[0]);
    }

    public void validateIfParkingLotExists() throws ParkingLotDoesNotExists {
        parkingLot.validateIfParkingLotExists();
    }

    public void validateIfParkingLotDoesExists() throws ParkingLotAlreadyCreated {
        parkingLot.validateIfParkingLotDoesExists();
    }
}
