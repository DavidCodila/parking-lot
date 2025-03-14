package command;

import java.util.ArrayList;
import java.util.List;

public class CommandGenerator {
    private final static int PARAMETER_INDEX = 1;
    private static final String PARK_CAR = "PARK_CAR";
    private static final String FIND = "FIND";
    private static final String UN_PARK_CAR = "UN_PARK_CAR";
    private static final String LIST = "LIST";

    public List<Command> generateCommands(String[] commandLines) {
        List<Command> commands = new ArrayList<>();
        for (String commandLine: commandLines) {
            Command command = generateCommand(commandLine);
            commands.add(command);
        }
        return commands;
    }

    private Command generateCommand(String commandLine) {
        String command = commandLine.split(" ")[0];
        int parameter = Integer.parseInt(commandLine.split(" ")[PARAMETER_INDEX]);
        return switch (command) {
            case PARK_CAR -> new ParkCarCommand(parameter);
            case FIND -> new FindCarCommand(parameter);
            case UN_PARK_CAR -> new UnParkCarCommand(parameter);
            case LIST -> new ListCarCommand(commandLine);
            default -> throw new IllegalStateException("Unexpected command: " + command);
        };
    }
}
