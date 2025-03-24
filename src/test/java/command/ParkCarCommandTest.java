package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ParkCarCommandTest {
    @Test
    public void testExecute() {
        final int integerID = 0;
        final List<String> ID = List.of(String.valueOf(integerID));
        ParkCarCommand parkCarCommand = new ParkCarCommand(ID);
        ParkingLot parkingLot = mock(ParkingLot.class);
        doNothing().when(parkingLot).parkCar(integerID);
        parkCarCommand.execute(parkingLot);
        verify(parkingLot, times(1)).parkCar(integerID);
    }
}
