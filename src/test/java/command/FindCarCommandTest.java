package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindCarCommandTest {
    FindCarCommand findCarCommand;

    @Test
    public void testExecute() {
        final List<String> ID = List.of("0");
        final int integerID = 0;
        this.findCarCommand = new FindCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).findCar(integerID);
        this.findCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).findCar(integerID);
    }

    @Test
    public void testThrowsExceptionForInvalidCommand() {
        final List<String> INVALID_COMMAND_STRING = List.of("Find", "Invalid", "Invalid");
        var exception = assertThrows(RuntimeException.class, () ->
                        this.findCarCommand = new FindCarCommand(INVALID_COMMAND_STRING)
        );
        assertEquals("Can not make Command from: " + INVALID_COMMAND_STRING, exception.getMessage());
    }

    @Test
    public void testThrowsExceptionForInvalidParameter() {
        final String INVALID_PARAMETER = "Invalid";
        final List<String> INVALID_PARAMETER_STRING = List.of(INVALID_PARAMETER);
        var exception = assertThrows(RuntimeException.class, () ->
                        this.findCarCommand = new FindCarCommand(INVALID_PARAMETER_STRING)
        );
        assertEquals( INVALID_PARAMETER + " is not a valid parameter", exception.getMessage());
    }
}
