package command;

import application.ParkingLot;

public class CommandRemote {
    private final ParkingLot parkingLot;

    public CommandRemote(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void execute(CommandInterface command) {
        command.execute(this.parkingLot);
    }
}
