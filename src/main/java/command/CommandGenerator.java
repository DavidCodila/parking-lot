package command;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class CommandGenerator {
    private static final String PARK_CAR = "PARK_CAR";
    private static final String FIND = "FIND";
    private static final String UN_PARK_CAR = "UN_PARK_CAR";
    private static final String LIST = "LIST";

    public List<CommandInterface> generateCommands(List<String> commandLines) throws IOException {
        return commandLines.stream()
                .map(this::generateCommand)
                .toList();
    }

    private CommandInterface generateCommand(String commandLine) {
        final String DELIMITER = " ";
        List<String> split = Arrays.stream(commandLine.split(DELIMITER)).toList();
        String command = split.getFirst();
        final int PARAMETER_INDEX = 1;
        List<String> parameterLineSplit = split.subList(PARAMETER_INDEX, split.size());

        return switch (command) {
            case PARK_CAR -> new ParkCarCommand(parameterLineSplit);
            case FIND -> new FindCarCommand(parameterLineSplit);
            case UN_PARK_CAR -> new UnParkCarCommand(parameterLineSplit);
            case LIST -> new ListCarCommand(parameterLineSplit);
            default -> throw new InvalidParameterException("Command " + command + " is not valid");
        };
    }
}
