package command;

import application.ParkingLot;

public class UnParkCarCommand implements CommandInterface {
    private final int id;

    public UnParkCarCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.unParkCar(id);
    }
}
