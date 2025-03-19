package command;

import java.security.InvalidParameterException;
import java.util.List;

public class CommandValidator {
    private final List<String> possibleCommands;

    public CommandValidator(List<String> possibleCommands) {
        this.possibleCommands = possibleCommands;
    }

    public void validateCommand(String command) {
        if (!this.possibleCommands.contains(command)) {
            throw new InvalidParameterException("Command " + command + " is not valid");
        }
    }

    public void validateParameter(String parameter) {
        try {
            Integer.parseInt(parameter);
        } catch (Exception e) {
            throw new InvalidParameterException("Parameter " + parameter + " is not valid");
        }
    }
}
