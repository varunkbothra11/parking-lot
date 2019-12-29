import com.apple.eawt.event.MagnificationEvent;
import constants.Messages;
import enums.Command;
import exceptions.InvalidCommandInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceUnParkVehicleTests extends ParkingLotServiceTests{
    @BeforeEach
    public void parkingVehicleTestSetup() throws Exception {
        super.testSetup();
        parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "2");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0001", "White");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0002", "Black");
    }

    @Test
    public void unParkVehicleTests() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.LEAVE, "2");
            Assertions.assertEquals(String.format(Messages.UNPARK_VEHICLE_MESSAGE, 2), result);
        });
    }

    @Test
    public void unParkVehicleWithNoInput() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.LEAVE, new String[]{});
        });
    }


    @Test
    public void unParkVehicleWithInvalidSlotNumber() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.LEAVE, "10");
        });
    }


    @Test
    public void unParkVehicleWithFreeSlotNumber() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            String result = parkingLotService.executeCommand(Command.LEAVE, "2");
            Assertions.assertEquals(String.format(Messages.UNPARK_VEHICLE_MESSAGE, 2), result);
            parkingLotService.executeCommand(Command.LEAVE, "2");
        });
    }
}
