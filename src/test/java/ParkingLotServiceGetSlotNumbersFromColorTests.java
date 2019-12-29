import constants.Messages;
import enums.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceGetSlotNumbersFromColorTests extends ParkingLotServiceTests {
    @BeforeEach
    public void slotNumbersFromColorTestSetup() throws InvalidCommandInputException, InvalidCommandException {
        super.testSetup();
        parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "4");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0001", "White");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0002", "Black");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0003", "White");
    }

    @Test
    public void getSlotNumbersFromColorTest() {
        Assertions.assertDoesNotThrow(() -> {
            String actualResult = parkingLotService.executeCommand(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, "White");
            String expectedResult = "1, 3";
            Assertions.assertEquals(expectedResult, actualResult);
        });
    }

    @Test
    public void getSlotNumbersFromColorNoVehicleFoundTest() {
        Assertions.assertDoesNotThrow(() -> {
            String actualResult = parkingLotService.executeCommand(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, "Red");
            String expectedResult = Messages.NOT_FOUND;
            Assertions.assertEquals(expectedResult, actualResult);
        });
    }

    @Test
        public void getSlotNumbersNumberFromColorWithNoInputTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
        });
    }

    @Test
    public void getSlotNumbersNumberFromColorWithInvalidColorTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, "Invalid color");
        });
    }
}
