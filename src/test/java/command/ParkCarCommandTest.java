package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ParkCarCommandTest {
    ParkCarCommand parkCarCommand;

    @Test
    public void testExecute() {
        final List<String> ID = List.of("0");
        final int integerID = 0;
        this.parkCarCommand = new ParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).parkCar(integerID);
        this.parkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).parkCar(integerID);
    }

    @Test
    public void testThrowsExceptionForInvalidCommand() {
        final List<String> INVALID_COMMAND_STRING = List.of("Find", "Invalid", "Invalid");
        var exception = assertThrows(InvalidParameterException.class, () ->
                this.parkCarCommand = new ParkCarCommand(INVALID_COMMAND_STRING)
        );
        assertEquals( "Parameter " + INVALID_COMMAND_STRING + " is not valid", exception.getMessage());
    }
}
