package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CommandTest {
    private static final String PARK_CAR_COMMAND_STRING = "PARK_CAR 0";
    private static final String FIND_COMMAND_STRING = "FIND 0";
    private static final String UN_PARK_CAR_COMMAND_STRING = "UN_PARK_CAR 0";
    private static final String LIST_COMMAND_STRING = "LIST 0 1";
    private static final String INVALID_COMMAND_STRING = "INVALID 0";
    private static final String INVALID_PARAMETER_STRING = "FIND INVALID";
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;

    @Test
    public void testGenerateParkCarCommand() {
        assertInstanceOf(ParkCarCommand.class, new Command(PARK_CAR_COMMAND_STRING).getCommand());
    }

    @Test
    public void testGenerateFindCarCommand() {
        assertInstanceOf(FindCarCommand.class, new Command(FIND_COMMAND_STRING).getCommand());
    }

    @Test
    public void testGenerateUnParkCarCommand() {
        assertInstanceOf(UnParkCarCommand.class, new Command(UN_PARK_CAR_COMMAND_STRING).getCommand());
    }

    @Test
    public void testGenerateListCarCommand() {
        assertInstanceOf(ListCarCommand.class, new Command(LIST_COMMAND_STRING).getCommand());
    }

    @Test
    public void testThrowExceptionForInvalidCommandString() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> new Command(INVALID_COMMAND_STRING));
        assertEquals("Command " + INVALID_COMMAND_STRING.split(DELIMITER)[COMMAND_INDEX] + " is not valid",
                exception.getMessage()
        );
    }

    @Test
    public void testThrowExceptionForInvalidParameterString() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> new Command(INVALID_PARAMETER_STRING));
        assertEquals("Parameter " + INVALID_PARAMETER_STRING.split(DELIMITER)[PARAMETER_INDEX] + " is not valid"
                , exception.getMessage()
        );
    }

    @Test
    public void testExecuteIsCalled() {
        Command command = new Command(PARK_CAR_COMMAND_STRING);
        ParkingLot parkingLot = mock(ParkingLot.class);
        ParkCarCommand parkCarCommand = mock(ParkCarCommand.class);
        command.setCommand(parkCarCommand);
        doNothing().when(parkCarCommand).execute(parkingLot);
        command.execute(parkingLot);
        verify(parkCarCommand, times(1)).execute(parkingLot);
    }
}
