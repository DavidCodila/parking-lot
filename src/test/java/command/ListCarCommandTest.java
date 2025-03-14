package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ListCarCommandTest {
    private static final String IDS = "0 1 2";

    @Test
    public void testExecute() {
        ListCarCommand listCarCommand = new ListCarCommand(IDS);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).listCars(any());
        listCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).listCars(any());
    }
}
