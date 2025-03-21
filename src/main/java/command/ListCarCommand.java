package command;

import application.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ListCarCommand implements CommandInterface {
    private final List<Integer> ids;

    public ListCarCommand(List<String> parameterLineSplit) {
        List<Integer> parameters = new ArrayList<>();
        for (String parameter : parameterLineSplit) {
            this.validateIsInteger(parameter);
            parameters.add(Integer.parseInt(parameter));
        }
        this.ids = parameters;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.listCars(this.ids);
    }
}
