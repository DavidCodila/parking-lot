package command;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CommandGenerator {
    private static final String PARK_CAR = "PARK_CAR";
    private static final String FIND = "FIND";
    private static final String UN_PARK_CAR = "UN_PARK_CAR";
    private static final String LIST = "LIST";
    private static final List<String> POSSIBLE_COMMANDS = List.of(PARK_CAR, FIND, UN_PARK_CAR, LIST);
    private static final int PARAMETER_INDEX = 1;

    private final CommandValidator commandValidator;

    public CommandGenerator() {
        this.commandValidator = new CommandValidator(POSSIBLE_COMMANDS);
    }

    public CommandInterface generateCommand(String commandLine) {
        final String DELIMITER = " ";
        String[] commandLineSplit = commandLine.split(DELIMITER);

        final int COMMAND_INDEX = 0;
        String command = commandLineSplit[COMMAND_INDEX];
        String parameterString = commandLineSplit[PARAMETER_INDEX];

        this.commandValidator.validateCommand(command);
        this.commandValidator.validateParameter(parameterString);

        int parameter = Integer.parseInt(parameterString);
        return switch (command) {
            case PARK_CAR -> new ParkCarCommand(parameter);
            case FIND -> new FindCarCommand(parameter);
            case UN_PARK_CAR -> new UnParkCarCommand(parameter);
            case LIST -> createNewListCarCommand(commandLineSplit);
            default -> throw new InvalidParameterException("Unexpected value: " + command);
        };
    }

    private ListCarCommand createNewListCarCommand(String[] commandLineSplit) {
        List<Integer> parameters = new ArrayList<>(commandLineSplit.length);
        for (int i = PARAMETER_INDEX; i < commandLineSplit.length; i++) {
            this.commandValidator.validateParameter(commandLineSplit[i]);
            parameters.add(Integer.parseInt(commandLineSplit[i]));
        }
        return new ListCarCommand(parameters);
    }
}
