package beans;

import exceptions.InvalidCommandInputException;
import services.logger.ILogger;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private ILogger log;
    private List<ParkingSlot> parkingSlots;

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

        this.parkingSlots = new ArrayList<ParkingSlot>(numberOfSlots);

        return "Created a parking lot with " + numberOfSlots + " slots";
    }

}
