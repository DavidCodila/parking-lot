package command;

import application.ParkingLot;
import command.interfaces.CommandInterface;

import java.util.List;

public class ListCarCommand implements CommandInterface {
    private final List<Integer> ids;

    public ListCarCommand(List<String> parameterLineSplit) {
        this.ids = this.extractValidParameterList(parameterLineSplit);
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.listCars(this.ids);
    }
}
