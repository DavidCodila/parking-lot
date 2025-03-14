package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UnParkCarCommandTest {
    private static final int ID = 0;

    @Test
    public void testExecute() {
        UnParkCarCommand unParkCarCommand = new UnParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).unParkCar(ID);
        unParkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).unParkCar(ID);
    }
}
