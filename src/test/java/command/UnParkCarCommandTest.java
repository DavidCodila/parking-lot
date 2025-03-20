package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UnParkCarCommandTest {
    @Test
    public void testExecute() {
        final int ID = 0;
        UnParkCarCommand unParkCarCommand = new UnParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).unParkCar(ID);
        unParkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).unParkCar(ID);
    }
}
