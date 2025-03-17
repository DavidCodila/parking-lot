package command;

import application.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ListCarCommand implements CommandInterface {
    private final List<Integer> ids = new ArrayList<>();

    public ListCarCommand(String commandLine) {
        String[] commandParameters = commandLine.split(" ");
        for (int i = 1; i < commandParameters.length; i++) {
            this.ids.add(Integer.parseInt(commandParameters[i]));
        }
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.listCars(this.ids);
    }
}
