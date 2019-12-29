import constants.Messages;
import enums.Command;
import exceptions.InvalidCommandInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceParkVehicleTests extends ParkingLotServiceTests {
    @BeforeEach
    public void parkingVehicleTestSetup() throws Exception {
        super.testSetup();
        parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "2");
    }

    @Test
    public void parkVehicleTest() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.PARK, "KA-01-HH-1234", "White");
            Assertions.assertEquals(String.format(Messages.PARKED_VEHICLE_MESSAGE, 1), result);
        });
    }

    @Test
    public void parkVehicleInFullParkingLot() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.PARK, "KA-01-HH-1234", "White");
            Assertions.assertEquals(String.format(Messages.PARKED_VEHICLE_MESSAGE, 1), result);

            result = parkingLotService.executeCommand(Command.PARK, "KA-01-HH-1234", "White");
            Assertions.assertEquals(String.format(Messages.PARKED_VEHICLE_MESSAGE, 2), result);

            result = parkingLotService.executeCommand(Command.PARK, "KA-01-HH-1234", "White");
            Assertions.assertEquals(Messages.PARKING_FULL_MESSAGE, result);
        });
    }

    @Test
    public void executeParkVehicleCommandWithNoInput() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.PARK, new String[]{});
        });
    }

    @Test
    public void executeParkVehicleCommandWithNoVehicleColor() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.PARK, "KA-01-HH-1234");
        });
    }
}
