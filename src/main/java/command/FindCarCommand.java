package command;

import application.ParkingLot;

import java.util.List;

public class FindCarCommand implements CommandInterface {
    private final int id;

    public FindCarCommand(List<String> parameterLineSplit) {
        if (parameterLineSplit.size() > 1) {
            throw new RuntimeException("Can not make Find Command from: " + parameterLineSplit);
        }
        String parameter = parameterLineSplit.getFirst();
        this.validateIsInteger(parameter);
        this.id = Integer.parseInt(parameter);
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.findCar(id);
    }
}
