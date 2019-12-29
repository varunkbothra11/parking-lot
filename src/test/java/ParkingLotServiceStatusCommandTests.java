import enums.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceStatusCommandTests extends BaseParkingLotServiceTests {
    @BeforeEach
    public void statusCommandTestSetup() throws Exception {
        super.testSetup();
        parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "2");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0001", "White");
        parkingLotService.executeCommand(Command.PARK, "KA-01-BB-0002", "Black");
    }

    @Test
    public void statusCommandTest() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.STATUS);
            String expectedResult = "Slot No.      Registration number     Color\n" +
                    "1             KA-01-BB-0001           White\n" +
                    "2             KA-01-BB-0002           Black";
            Assertions.assertEquals(expectedResult, result);
        });
    }
}
