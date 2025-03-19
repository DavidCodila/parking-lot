package command;

import application.ParkingLot;

import java.util.List;

public class CommandRemote {
    private final ParkingLot parkingLot;

    public CommandRemote(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void executeCommands(List<CommandInterface> commands) {
        commands.forEach(this::execute);
    }

    private void execute(CommandInterface command) {
        command.execute(this.parkingLot);
    }
}
