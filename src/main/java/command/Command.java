package command;

import application.ParkingLot;
import com.google.common.annotations.VisibleForTesting;

import java.security.InvalidParameterException;
import java.util.List;

//responsible for construction of concrete command objects
public class Command implements CommandInterface {
    private static final String PARK_CAR = "PARK_CAR";
    private static final String FIND = "FIND";
    private static final String UN_PARK_CAR = "UN_PARK_CAR";
    private static final String LIST = "LIST";
    private static final List<String> POSSIBLE_COMMANDS = List.of(PARK_CAR, FIND, UN_PARK_CAR, LIST);
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;

    private CommandInterface concreteCommandObject;

    public Command(String commandLine) {
        String[] commandLineSplit = commandLine.split(DELIMITER);
        this.validate(commandLineSplit);
        String command = commandLineSplit[COMMAND_INDEX];
        int parameter = Integer.parseInt(commandLineSplit[PARAMETER_INDEX]);
        this.concreteCommandObject = switch (command) {
            case PARK_CAR -> new ParkCarCommand(parameter);
            case FIND -> new FindCarCommand(parameter);
            case UN_PARK_CAR -> new UnParkCarCommand(parameter);
            case LIST -> new ListCarCommand(commandLine);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }

    private void validate(String[] commandLineSplit) {
        String command = commandLineSplit[COMMAND_INDEX];
        if (!POSSIBLE_COMMANDS.contains(command)) {
            throw new InvalidParameterException("Command " + command + " is not valid");
        }
        String parameter = commandLineSplit[PARAMETER_INDEX];
        try {
            Integer.parseInt(parameter);
        } catch (Exception e) {
            throw new InvalidParameterException("Parameter " + parameter + " is not valid");
        }
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        this.concreteCommandObject.execute(parkingLot);
    }

    @VisibleForTesting
    CommandInterface getCommand() {
        return this.concreteCommandObject;
    }

    @VisibleForTesting
    void setCommand(CommandInterface command) {
        this.concreteCommandObject = command;
    }
}
