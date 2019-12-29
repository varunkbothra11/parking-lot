package enums;

import java.util.Arrays;
import java.util.Optional;

public enum Color {
    RED("name"),
    GREEN("green"),
    BLUE("blue"),
    BLACK("black"),
    WHITE("white");

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
