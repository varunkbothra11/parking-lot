import enums.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ParkingLotService;
import services.logger.BlackHoleLogger;
import services.logger.ILogger;

import java.util.Arrays;
import java.util.List;

/**
 * @author varun.bothra
 */
public class ParkingLotServiceIntegrationTests {
    private static ParkingLotService parkingLotService;

    @BeforeEach
    public void testSetup() {
        ILogger log = new BlackHoleLogger();
        parkingLotService = new ParkingLotService(log);
    }

    @Test
    public void executeFileTest() {
        String resourceName = "file_test_inputs1.txt";
        String filePath = getClass().getResource(resourceName).getPath();
        final List<String> actualResult = parkingLotService.executeFile(filePath);
        final List<String> expectedResult = getExpectedResult();

        Assertions.assertEquals(expectedResult.size(), actualResult.size());

        for (int i = 0; i < expectedResult.size(); ++i) {
            Assertions.assertTrue(expectedResult.get(i).equalsIgnoreCase(actualResult.get(i)));
        }
    }

    @Test
    public void executeCommandTest() throws Exception {
        List<CommandModel> commandModels = getCommandModels();
        for (CommandModel commandModel : commandModels) {
            String actualResult = parkingLotService.executeCommand(commandModel.getCommand(), commandModel.getInput());
            Assertions.assertEquals(commandModel.getExpectedResult(), actualResult);
        }
    }

    private List<String> getExpectedResult() {
        return Arrays.asList("Created a parking lot with 6 slots",
                "Allocated slot number: 1",
                "Allocated slot number: 2",
                "Allocated slot number: 3",
                "Allocated slot number: 4",
                "Allocated slot number: 5",
                "Allocated slot number: 6",
                "Slot number 4 is free",
                "Slot No.      Registration number     Color\n" +
                        "1             KA-01-HH-1234           White\n" +
                        "2             KA-01-HH-9999           White\n" +
                        "3             KA-01-BB-0001           Black\n" +
                        "5             KA-01-HH-2701           Blue\n" +
                        "6             KA-01-HH-3141           Black",
                "Allocated slot number: 4",
                "Sorry, parking lot is full",
                "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333",
                "1, 2, 4",
                "6",
                "Not found");
    }

    private List<CommandModel> getCommandModels() {
        return Arrays.asList(
                new CommandModel(Command.CREATE_PARKING_LOT, new String[]{"6"}, "Created a parking lot with 6 slots"),
                new CommandModel(Command.PARK, new String[]{"KA-01-HH-1234", "White"}, "Allocated slot number: 1"),
                new CommandModel(Command.PARK, new String[]{"KA-01-HH-9999", "White"}, "Allocated slot number: 2"),
                new CommandModel(Command.PARK, new String[]{"KA-01-BB-0001", "Black"}, "Allocated slot number: 3"),
                new CommandModel(Command.PARK, new String[]{"KA-01-HH-7777", "Red"}, "Allocated slot number: 4"),
                new CommandModel(Command.PARK, new String[]{"KA-01-HH-2701", "Blue"}, "Allocated slot number: 5"),
                new CommandModel(Command.PARK, new String[]{"KA-01-HH-3141", "Black"}, "Allocated slot number: 6"),
                new CommandModel(Command.LEAVE, new String[]{"4"}, "Slot number 4 is free"),
                new CommandModel(Command.STATUS, new String[]{}, "Slot No.      Registration number     Color\n" +
                        "1             KA-01-HH-1234           White\n" +
                        "2             KA-01-HH-9999           White\n" +
                        "3             KA-01-BB-0001           Black\n" +
                        "5             KA-01-HH-2701           Blue\n" +
                        "6             KA-01-HH-3141           Black"),
                new CommandModel(Command.PARK, new String[]{"KA-01-P-333", "White"}, "Allocated slot number: 4"),
                new CommandModel(Command.PARK, new String[]{"DL-12-AA-9999", "White"}, "Sorry, parking lot is full"),
                new CommandModel(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR, new String[]{"White"}, "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333"),
                new CommandModel(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, new String[]{"White"}, "1, 2, 4"),
                new CommandModel(Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER, new String[]{"KA-01-HH-3141"}, "6"),
                new CommandModel(Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER, new String[]{"MH-04-AY-1111"}, "Not found")
        );
    }

    private class CommandModel {
        private Command command;
        private String[] input;
        private String expectedResult;

        public CommandModel(Command command, String[] input, String expectedResult) {
            this.command = command;
            this.input = input;
            this.expectedResult = expectedResult;
        }

        public Command getCommand() {
            return this.command;
        }

        public String[] getInput() {
            return this.input;
        }

        public String getExpectedResult() {
            return this.expectedResult;
        }
    }
}
