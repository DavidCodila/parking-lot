package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ParkCarCommandTest {
    private static final int ID = 0;

    @Test
    public void testExecute() {
        ParkCarCommand parkCarCommand = new ParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).parkCar(ID);
        parkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).parkCar(ID);
    }
}
