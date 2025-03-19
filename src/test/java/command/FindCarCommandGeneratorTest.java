package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FindCarCommandGeneratorTest {
    private static final int ID = 0;

    @Test
    public void testExecute() {
        FindCarCommand findCarCommand = new FindCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).findCar(ID);
        findCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).findCar(ID);
    }
}
