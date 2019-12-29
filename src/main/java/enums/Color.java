package enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author varun.bothra
 */
public enum Color {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    BLACK("Black"),
    WHITE("White");

    private String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Color getCommandEnum(String colorString) {
        Optional<Color> colorOptional = Arrays.stream(Color.values())
                .filter(color -> color.getName().equalsIgnoreCase(colorString))
                .findFirst();

        if (colorOptional.isPresent()) {
            return colorOptional.get();
        }

        return null;
    }
}
