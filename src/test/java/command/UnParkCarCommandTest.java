package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class UnParkCarCommandTest {
    @Test
    public void testExecute() {
        final int integerID = 0;
        final List<String> ID = List.of(String.valueOf(integerID));
        UnParkCarCommand unParkCarCommand = new UnParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).unParkCar(integerID);
        unParkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).unParkCar(integerID);
    }
}
