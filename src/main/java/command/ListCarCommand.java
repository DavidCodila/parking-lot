package command;

import application.ParkingLot;

import java.util.List;

public class ListCarCommand implements Command {
    private final List<Integer> ids;

    public ListCarCommand(String commandLine) {
        this.ids = null; //need to come back to this
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.print(ids);
    }
}
