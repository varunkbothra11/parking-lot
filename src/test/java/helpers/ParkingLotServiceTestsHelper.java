package helpers;

import java.util.Arrays;
import java.util.List;

public class ParkingLotServiceTestsHelper {
    public static List<String> getExpectedResult() {
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
}
