import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ParkingLotService;
import services.logger.BlackHoleLogger;
import services.logger.ILogger;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceTests {
    protected static ParkingLotService parkingLotService;

    @BeforeEach
    public void testSetup() {
        ILogger logger = new BlackHoleLogger();
        parkingLotService = new ParkingLotService(logger);
    }

    @Test
    public void invalidCommandTest() {
        Assertions.assertThrows(InvalidCommandException.class, () -> {
            parkingLotService.executeCommand(null, "3");
        });
    }
}
