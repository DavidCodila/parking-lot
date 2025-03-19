package command;

import application.ParkingLot;

import java.util.List;

public class ListCarCommand implements CommandInterface {
    private final List<Integer> ids;

    public ListCarCommand(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.listCars(this.ids);
    }
}
