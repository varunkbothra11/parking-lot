package beans;

import enums.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.ParkingLotService;
import services.logger.BlackHoleLogger;
import services.logger.ILogger;

public class ParkingLotCreateCommandTests {
    private static ParkingLotService parkingLotService;

    @BeforeAll
    public static void testSetup() {
        ILogger logger = new BlackHoleLogger();
        parkingLotService = new ParkingLotService(logger);
    }

    @Test
    public void executeCommand_CreateParkingLot_Test() {
        Assertions.assertDoesNotThrow(() -> {
            String result = parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "3");
            Assertions.assertEquals("Created a parking lot with 3 slots", result);
        });
    }

    @Test
    public void executeCommand_InvalidCommand_Test() {
        Assertions.assertThrows(InvalidCommandException.class, () -> {
            parkingLotService.executeCommand(null, "3");
        });
    }

    @Test
    public void executeCommand_CreateParkingLot_NoInputPassed_Test() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, new String[]{});
        });
    }

    @Test
    public void executeCommand_CreateParkingLot_ZeroSlotsPassed_Test() {
        Assertions.assertThrows(InvalidCommandInputException.class, () -> {
            parkingLotService.executeCommand(Command.CREATE_PARKING_LOT, "0");
        });
    }
}
