package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandGeneratorTest {
    CommandGenerator commandGenerator;

    @BeforeEach
    public void setUp() {
        this.commandGenerator = new CommandGenerator();
    }

    @Test
    public void testGetCommands() throws IOException {
        final int PARK_CAR_COMMAND_INDEX = 0;
        final int FIND_COMMAND_INDEX = 1;
        final int LIST_COMMAND_INDEX = 2;
        final int UN_PARK_COMMAND_INDEX = 3;
        List<String> commandsAsString = List.of("PARK_CAR 0", "FIND 0", "LIST 0 1", "UN_PARK_CAR 1");
        List<CommandInterface> result = commandGenerator.generateCommands(commandsAsString);
        assertInstanceOf(ParkCarCommand.class, result.get(PARK_CAR_COMMAND_INDEX));
        assertInstanceOf(FindCarCommand.class, result.get(FIND_COMMAND_INDEX));
        assertInstanceOf(ListCarCommand.class, result.get(LIST_COMMAND_INDEX));
        assertInstanceOf(UnParkCarCommand.class, result.get(UN_PARK_COMMAND_INDEX));
    }

    @Test
    public void testGetCommandsThrowsExceptionForInvalidCommand() {
        final List<String> INCORRECT_COMMAND = List.of("INCORRECT_COMMAND");
        var exception = assertThrows(InvalidParameterException.class, () ->
                this.commandGenerator.generateCommands(INCORRECT_COMMAND)
        );
        assertEquals("Command " + INCORRECT_COMMAND.getFirst() + " is not valid", exception.getMessage());
    }
}
