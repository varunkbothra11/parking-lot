import enums.Command;
import exceptions.InvalidCommandException;
import exceptions.ParkingLotDoesNotExists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceTests extends BaseParkingLotServiceTests {
    @Test
    public void invalidCommandTest() {
        Assertions.assertThrows(InvalidCommandException.class, () -> {
            parkingLotService.executeCommand(null, "3");
        });
    }

    @Test
    public void getStatusWhenParkingLotNotCreatedTest() {
        Assertions.assertThrows(ParkingLotDoesNotExists.class, () -> {
            parkingLotService.executeCommand(Command.STATUS);
        });
    }
}
