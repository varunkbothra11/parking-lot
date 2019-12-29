import helpers.ParkingLotServiceTestsHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

public class MainTests {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void testSetup() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void executeFileTest() {
        String resourceName = "file_test_inputs1.txt";
        String filePath = getClass().getResource(resourceName).getPath();
        Main.main(new String[]{filePath});
        String expectedOutput = ParkingLotServiceTestsHelper.getExpectedResult().stream().collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedOutput, outContent.toString().trim());
    }
}
