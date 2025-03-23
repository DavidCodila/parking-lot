package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ListCarCommandTest {
    ListCarCommand listCarCommand;


    @Test
    public void testExecute() {
        final List<String> IDS = List.of("1", "2", "3");
        final List<Integer> integerIDS = List.of(1, 2, 3);
        this.listCarCommand = new ListCarCommand(IDS);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).listCars(integerIDS);
        this.listCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).listCars(integerIDS);
    }

    @Test
    public void testThrowsExceptionForInvalidCommand() {
        String invalidString = "Invalid";
        final List<String> INVALID_COMMAND_STRING = List.of(invalidString);
        var exception = assertThrows(InvalidParameterException.class, () ->
                this.listCarCommand = new ListCarCommand(INVALID_COMMAND_STRING)
        );
        assertEquals("Parameter " + INVALID_COMMAND_STRING + " is not valid", exception.getMessage());
    }
}
