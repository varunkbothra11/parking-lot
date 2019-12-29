import enums.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceGetRegistrationNumberFromColorTests extends ParkingLotServiceTests {
    @BeforeEach
    public void registrationNumberFromColorTestSetup() throws InvalidCommandInputException, InvalidCommandException {
        super.testSetup();
        parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "4");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0001", "White");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0002", "Black");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0003", "White");
    }

    @Test
    public void getRegistrationNumberFromColorTest() {
        Assertions.assertDoesNotThrow(() -> {
            String actualResult = parkingLotService.executeCommand(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR, "White");
            String expectedResult = "KA-01-BB-0001, KA-01-BB-0003";
            Assertions.assertEquals(expectedResult, actualResult);
        });
    }

    @Test
    public void getRegistrationNumberFromColorWithNoInputTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
        });
    }

    @Test
    public void getRegistrationNumberFromColorWithInvalidColorTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR, "Invalid color");
        });
    }
}
