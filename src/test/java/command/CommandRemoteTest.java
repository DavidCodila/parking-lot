package command;

import application.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class CommandRemoteTest {
    @Test
    public void testExecuteCommands() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        CommandRemote commandRemote = new CommandRemote(parkingLot);
        CommandInterface command = mock(CommandInterface.class);
        CommandInterface command2 = mock(CommandInterface.class);
        doNothing().when(command).execute(any());
        doNothing().when(command2).execute(any());
        commandRemote.executeCommands(List.of(command, command2));
        verify(command, times(1)).execute(any());
    }
}
