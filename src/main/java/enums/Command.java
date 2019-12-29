package enums;

import java.util.Arrays;
import java.util.Optional;

public enum Command {
    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number"),
    EXIT("exit");

    private String name;

    Command(String name) {
        this.name = name;
    }

    private String getName() {
        return this.name;
    }

    public static Command getCommandEnum(String commandString) {
        Optional<Command> commandOptional = Arrays.stream(Command.values())
                .filter(command -> command.getName().equalsIgnoreCase(commandString))
                .findFirst();

        if (commandOptional.isPresent()) {
            return commandOptional.get();
        }

        return null;
    }
}
