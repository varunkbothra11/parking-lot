package beans;

import constants.Messages;
import enums.Command;
import exceptions.InvalidCommandInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotCreateCommandTests extends ParkingLotTests {
    @Test
    public void createParkingLotTest() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "3");
            Assertions.assertEquals(String.format(Messages.CREATE_PARKING_LOT_MESSAGE, 3), result);
        });
    }

    @Test
    public void createParkingLotWithnoInputTest() {
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
