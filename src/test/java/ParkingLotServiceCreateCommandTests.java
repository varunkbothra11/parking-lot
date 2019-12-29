import constants.Messages;
import enums.Command;
import exceptions.InvalidCommandInputException;
import exceptions.ParkingLotAlreadyCreated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceCreateCommandTests extends BaseParkingLotServiceTests {
    @Test
    public void createParkingLotTest() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "3");
            Assertions.assertEquals(String.format(Messages.CREATE_PARKING_LOT_MESSAGE, 3), result);
        });
    }

    @Test
    public void createParkingLotWithInvalidSlotNumber() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            String result = parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "sdkjfb");
            Assertions.assertEquals(String.format(Messages.CREATE_PARKING_LOT_MESSAGE, 3), result);
        });
    }

    @Test
    public void createParkingLotWhenAlreadyCreatedTest() {
        Assertions.assertThrows(ParkingLotAlreadyCreated.class, () -> {
            parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "3");
            parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "3");
        });
    }

    @Test
    public void createParkingLotWithNoInputTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, new String[]{});
        });
    }

    @Test
    public void createParkingLotWithZeroSlotsTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "0");
        });
    }
}
