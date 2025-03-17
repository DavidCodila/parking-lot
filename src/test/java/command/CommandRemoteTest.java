package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CommandRemoteTest {
    @Test
    public void testExecuteCommand() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        CommandRemote commandRemote = new CommandRemote(parkingLot);
        CommandInterface command = mock(CommandInterface.class);
        doNothing().when(command).execute(any());
        commandRemote.execute(command);
        verify(command, times(1)).execute(any());
    }
}
