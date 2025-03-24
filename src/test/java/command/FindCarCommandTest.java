package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class FindCarCommandTest {
    @Test
    public void testExecute() {
        final int integerID = 0;
        final List<String> ID = List.of(String.valueOf(integerID));
        FindCarCommand findCarCommand = new FindCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).findCar(integerID);
        findCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).findCar(integerID);
    }
}
