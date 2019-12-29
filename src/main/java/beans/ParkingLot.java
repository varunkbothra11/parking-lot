package beans;

import constants.ErrorMessages;
import constants.Messages;
import constants.TableRepresentations;
import enums.Color;
import exceptions.InvalidCommandInputException;
import services.logger.ILogger;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author varun.bothra
 */
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
            throw new InvalidCommandInputException(ErrorMessages.INVALID_SLOT_NUMBER_VALUE);
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
            throw new InvalidCommandInputException(ErrorMessages.INVALID_SLOT_NUMBER);
        }

        if (this.vehicleParkedInSlot.get(slotNumber) == null) {
            throw new InvalidCommandInputException(ErrorMessages.NO_VEHICLE_PARKED);
        }

        this.vehicleParkedInSlot.remove(slotNumber);
        this.parkingSlots.add(slotNumber);

        return String.format(Messages.UNPARK_VEHICLE_MESSAGE, slotNumber);
    }

    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(TableRepresentations.HEADER_FORMAT, TableRepresentations.SLOT_HEADER_NAME, TableRepresentations.REGISTRATION_NUMBER_HEADER_NAME, TableRepresentations.COLOR_HEADER_NAME));

        for (int i = 1; i <= this.totalParkingSlots; ++i) {
            if (this.vehicleParkedInSlot.containsKey(i)) {
                Vehicle vehicle = this.vehicleParkedInSlot.get(i);
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append(String.format(TableRepresentations.CONTENT_FORMAT, i, vehicle.getLicenseNumber(), vehicle.getColor().getName()));
            }
        }

        return stringBuilder.toString();
    }

    public String getVehicleRegistrationNumbers(Color color) {
        String result = vehicleParkedInSlot.values()
                .stream()
                .filter(vehicle -> vehicle.getColor().equals(color))
                .map(vehicle -> vehicle.getLicenseNumber())
                .collect(Collectors.joining(", "));

        return getResult(result);
    }

    public String getSlotNumbers(Color color) {
        String result = vehicleParkedInSlot.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getColor().equals(color))
                .map(entry -> String.valueOf(entry.getKey()))
                .collect(Collectors.joining(", "));

        return getResult(result);
    }

    public String getSlotNumber(String licenseNumber) {
        String result = vehicleParkedInSlot.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getLicenseNumber().equals(licenseNumber))
                .map(entry -> String.valueOf(entry.getKey()))
                .collect(Collectors.joining(", "));

        return getResult(result);
    }

    private String getResult(String result) {
        if(result.isEmpty()) {
            return Messages.NOT_FOUND;
        }

        return result;
    }
}
