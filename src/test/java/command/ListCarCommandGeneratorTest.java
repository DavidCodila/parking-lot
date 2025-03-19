package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ListCarCommandGeneratorTest {
    private static final List<Integer> IDS = List.of(1, 2, 3);

    @Test
    public void testExecute() {
        ListCarCommand listCarCommand = new ListCarCommand(IDS);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).listCars(any());
        listCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).listCars(any());
    }
}
