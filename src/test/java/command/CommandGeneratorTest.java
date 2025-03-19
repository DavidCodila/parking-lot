package command;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class CommandGeneratorTest {
    private static final String PARK_CAR_COMMAND_STRING = "PARK_CAR 0";
    private static final String FIND_COMMAND_STRING = "FIND 0";
    private static final String UN_PARK_CAR_COMMAND_STRING = "UN_PARK_CAR 0";
    private static final String LIST_COMMAND_STRING = "LIST 0 1";
    private static final String INVALID_COMMAND_STRING = "INVALID 0";
    private static final String INVALID_PARAMETER_STRING = "FIND INVALID";
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;

    CommandGenerator commandGenerator = new CommandGenerator();

    @Test
    public void testGenerateCommandParkCarCommand() {
        assertInstanceOf(ParkCarCommand.class, this.commandGenerator.generateCommand(PARK_CAR_COMMAND_STRING));
    }

    @Test
    public void testGenerateCommandFindCarCommand() {
        assertInstanceOf(FindCarCommand.class, this.commandGenerator.generateCommand(FIND_COMMAND_STRING));
    }

    @Test
    public void testGenerateCommandUnParkCarCommand() {
        assertInstanceOf(UnParkCarCommand.class, this.commandGenerator.generateCommand(UN_PARK_CAR_COMMAND_STRING));
    }

    @Test
    public void testGenerateCommandListCarCommand() {
        assertInstanceOf(ListCarCommand.class, this.commandGenerator.generateCommand(LIST_COMMAND_STRING));
    }

    @Test
    public void testThrowExceptionForInvalidCommandString() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.commandGenerator.generateCommand(INVALID_COMMAND_STRING));
        assertEquals("Command " + INVALID_COMMAND_STRING.split(DELIMITER)[COMMAND_INDEX] + " is not valid",
                exception.getMessage()
        );
    }

    @Test
    public void testThrowExceptionForInvalidParameterString() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.commandGenerator.generateCommand(INVALID_PARAMETER_STRING));
        assertEquals("Parameter " + INVALID_PARAMETER_STRING.split(DELIMITER)[PARAMETER_INDEX] + " is not valid"
                , exception.getMessage()
        );
    }
}
