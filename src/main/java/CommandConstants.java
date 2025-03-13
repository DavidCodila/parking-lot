import java.util.List;

public class CommandConstants {
    public static final String PARK_CAR = "PARK_CAR";
    public static final String FIND = "FIND";
    public static final String UN_PARK_CAR = "UN_PARK_CAR";
    public static final String LIST = "LIST";
    public static final List<String> possibleCommands = List.of("PARK_CAR", "FIND", "UN_PARK_CAR", "LIST");

    //still need to add validation for command parameters
    public static void validateCommandLines(String[] listOfCommandLines) {
        for (String commandLine : listOfCommandLines) {
            String command = commandLine.split(" ")[0];
            if (!possibleCommands.contains(command)) {
                throw new RuntimeException("Command " + command + " is not a proper command");
            }
        }
    }

    public static Command createCommand(String command, int parameter) {
        return switch (command) {
            case PARK_CAR -> new ParkCarCommand(parameter);
            case FIND -> new FindCarCommand(parameter);
            case UN_PARK_CAR -> new UnParkCarCommand(parameter);
            //case CommandConstants.LIST -> new (parameter); //need to come back to
            default -> throw new IllegalStateException("Unexpected command: " + command);
        };
    }
}
