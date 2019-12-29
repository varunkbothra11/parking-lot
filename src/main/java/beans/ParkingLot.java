package beans;

import constants.Messages;
import enums.Color;
import exceptions.InvalidCommandInputException;
import services.logger.ILogger;

import java.util.*;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private ILogger log;
    private Queue<ParkingSlot> parkingSlots;
    private Map<ParkingSlot, Vehicle> vehicleParkedInSlot;

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

        return String.format(Messages.CREATE_PARKING_LOT_MESSAGE, numberOfSlots);
    }

    public String parkVehicle(String licenseNumber, Color color) {
        Vehicle vehicle = new Vehicle(licenseNumber, color);

        if (parkingSlots.isEmpty()) {
            return Messages.PARKING_FULL_MESSAGE;
        }

        ParkingSlot parkingSlot = parkingSlots.remove();
        vehicleParkedInSlot.putIfAbsent(parkingSlot, vehicle);

        return String.format(Messages.PARKED_VEHICLE_MESSAGE, parkingSlot.getId());
    }

    private Queue<ParkingSlot> createParkingSlots(int numberOfSlots) {

        // This is required t
        Queue<ParkingSlot> parkingSlots = new PriorityQueue<>(new ParkingSlotComparator());

        for (int i = 1; i <= numberOfSlots; ++i) {
            parkingSlots.add(new ParkingSlot(i));
        }

        return parkingSlots;
    }

    private static class ParkingSlotComparator implements Comparator<ParkingSlot> {
        @Override
        public int compare(ParkingSlot slot1, ParkingSlot slot2) {
            if (slot1.getId() < slot2.getId()) {
                return -1;
            } else if (slot1.getId() > slot2.getId()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
