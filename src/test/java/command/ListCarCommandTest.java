package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ListCarCommandTest {
    @Test
    public void testExecute() {
        final List<String> IDS = List.of("1", "2", "3");
        final List<Integer> integerIDS = List.of(1, 2, 3);
        ListCarCommand listCarCommand = new ListCarCommand(IDS);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).listCars(integerIDS);
        listCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).listCars(integerIDS);
    }
}
