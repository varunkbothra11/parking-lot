package helpers;

import beans.ParkingLot;
import enums.Color;
import exceptions.InvalidCommandInputException;

public class ParkingLotHelper {
    private static ParkingLot parkingLot;

    public ParkingLotHelper(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String createParkingLot(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException("Please specify the number of slots to create");
        }

        int numberOfSlots = Integer.parseInt(inputs[0]);
        return parkingLot.createParkingLot(numberOfSlots);
    }

    public String parkVehicle(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length < 2) {
            throw new InvalidCommandInputException("Please specify the car license number and the color");
        }

        String licenseNumber = inputs[0];
        Color color = Color.getCommandEnum(inputs[1]);
        return parkingLot.parkVehicle(licenseNumber, color);
    }

    public static String unParkVehicle(String[] inputs) throws InvalidCommandInputException {
        if (inputs.length == 0) {
            throw new InvalidCommandInputException("Please enter the slot number to un-park the vehicle");
        }

        int slotNumber = Integer.parseInt(inputs[0]);
        return parkingLot.unParkVehicle(slotNumber);
    }
}
