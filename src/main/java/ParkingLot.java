import enums.Command;

import java.util.List;

public class ParkingLot {
    private static ParkingLot parkingLot;

    private ParkingLot() {

    }

    public static void main(String[] args) {
    }

    public static ParkingLot getInstance() {
        synchronized(parkingLot) {
            if(parkingLot == null) {
                parkingLot = new ParkingLot();
            }

            return parkingLot;
        }
    }

    public List<String> executeFile(String filePath) {
        return null;
    }

    public String executeCommand(Command command, String... inputs) {
        return null;
    }
}
