package beans;

import constants.Messages;
import enums.Color;
import exceptions.InvalidCommandInputException;
import services.logger.ILogger;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private ILogger log;
    private int totalParkingSlots;
    private Queue<Integer> parkingSlots;
    private Map<Integer, Vehicle> vehicleParkedInSlot;

    private ParkingLot(ILogger log) {
        this.log = log;
    }

    public static ParkingLot getInstance(ILogger log) {
        synchronized (ParkingLot.class) {
            if (parkingLot == null) {
                parkingLot = new ParkingLot(log);
                return parkingLot;
            }

            return parkingLot;
        }
    }

    public String createParkingLot(int numberOfSlots) throws InvalidCommandInputException {
        if (numberOfSlots == 0) {
            throw new InvalidCommandInputException("Atleast one slot is required to create parking lot.");
        }

        this.parkingSlots = createParkingSlots(numberOfSlots);
        this.vehicleParkedInSlot = new HashMap<>();
        this.totalParkingSlots = numberOfSlots;

        return String.format(Messages.CREATE_PARKING_LOT_MESSAGE, numberOfSlots);
    }

    public String parkVehicle(String licenseNumber, Color color) {
        Vehicle vehicle = new Vehicle(licenseNumber, color);

        if (parkingSlots.isEmpty()) {
            return Messages.PARKING_FULL_MESSAGE;
        }

        Integer parkingSlot = parkingSlots.remove();
        vehicleParkedInSlot.putIfAbsent(parkingSlot, vehicle);

        return String.format(Messages.PARKED_VEHICLE_MESSAGE, parkingSlot);
    }

    private Queue<Integer> createParkingSlots(int numberOfSlots) {

        // Priority queue is required to always allocate the nearest parking slot.
        Queue<Integer> parkingSlots = new PriorityQueue<>();

        for (int i = 1; i <= numberOfSlots; ++i) {
            parkingSlots.add(i);
        }

        return parkingSlots;
    }

    public String unParkVehicle(int slotNumber) throws InvalidCommandInputException {
        if (slotNumber > this.totalParkingSlots) {
            throw new InvalidCommandInputException("Invalid slot number");
        }

        if (this.vehicleParkedInSlot.get(slotNumber) == null) {
            throw new InvalidCommandInputException("No vehicle parking in the specified slot");
        }

        this.vehicleParkedInSlot.remove(slotNumber);
        this.parkingSlots.add(slotNumber);

        return String.format(Messages.UNPARK_VEHICLE_MESSAGE, slotNumber);
    }
}
