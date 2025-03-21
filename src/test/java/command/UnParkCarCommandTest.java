package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UnParkCarCommandTest {
    UnParkCarCommand unParkCarCommand;
    @Test
    public void testExecute() {
        final List<String> ID = List.of("0");
        final int integerID = 0;
        this.unParkCarCommand = new UnParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).unParkCar(integerID);
        unParkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).unParkCar(integerID);
    }

    @Test
    public void testThrowsExceptionForInvalidCommand() {
        final List<String> INVALID_COMMAND_STRING = List.of("Find", "Invalid", "Invalid");
        var exception = assertThrows(RuntimeException.class, () ->
                this.unParkCarCommand = new UnParkCarCommand(INVALID_COMMAND_STRING)
        );
        assertEquals("Can not make UnParkCar Command from: " + INVALID_COMMAND_STRING, exception.getMessage());
    }
}
