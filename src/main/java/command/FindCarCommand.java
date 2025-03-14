package command;

import application.ParkingLot;

public class FindCarCommand implements Command {
    private final int id;

    public FindCarCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.findCar(id);
    }
}
