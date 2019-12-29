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
public class ParkingLotServiceGetSlotNumberFromLicenseNumberTests extends ParkingLotServiceTests {
    @BeforeEach
    public void slotNumberFromLicenseNumberTestSetup() throws InvalidCommandInputException, InvalidCommandException {
        super.testSetup();
        parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "4");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0001", "White");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0002", "Black");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0003", "White");
    }

    @Test
    public void slotNumberFromLicenseNumberTest() {
        Assertions.assertDoesNotThrow(() -> {
            String actualResult = parkingLotService.executeCommand(Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER, "KA-01-BB-0001");
            String expectedResult = "1";
            Assertions.assertEquals(expectedResult, actualResult);
        });
    }

    @Test
    public void getslotNumberFromLicenseNumberNoVehicleFoundTest() {
        Assertions.assertDoesNotThrow(() -> {
            String actualResult = parkingLotService.executeCommand(Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER, "Random license number");
            String expectedResult = Messages.NOT_FOUND;
            Assertions.assertEquals(expectedResult, actualResult);
        });
    }

    @Test
    public void getslotNumberFromLicenseNumberWithNoInputTest() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER);
        });
    }
}
