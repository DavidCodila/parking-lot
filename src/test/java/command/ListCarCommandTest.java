package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ListCarCommandTest {
    @Test
    public void testExecute() {
        final List<Integer> IDS = List.of(1, 2, 3);
        ListCarCommand listCarCommand = new ListCarCommand(IDS);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).listCars(IDS);
        listCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).listCars(IDS);
    }
}
