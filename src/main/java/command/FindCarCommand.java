package command;

import application.ParkingLot;

public class FindCarCommand implements CommandInterface {
    private final int id;

    public FindCarCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.findCar(id);
    }
}
