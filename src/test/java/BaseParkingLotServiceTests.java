import org.junit.jupiter.api.BeforeEach;
import services.ParkingLotService;
import services.logger.BlackHoleLogger;
import services.logger.ILogger;

/**
 * @author varun.bothra
 */
public abstract class BaseParkingLotServiceTests {
    protected static ParkingLotService parkingLotService;

    @BeforeEach
    public void testSetup() {
        ILogger logger = new BlackHoleLogger();
        parkingLotService = new ParkingLotService(logger);
    }
}
