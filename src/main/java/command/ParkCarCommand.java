package command;

import application.ParkingLot;

import java.util.List;

public class ParkCarCommand implements CommandInterface {
    private final int id;

    public ParkCarCommand(List<String> parameterLineSplit) {
        if (parameterLineSplit.size() > 1) {
            throw new RuntimeException("Can not make ParkCar Command from: " + parameterLineSplit);
        }
        String parameter = parameterLineSplit.getFirst();
        this.validateIsInteger(parameter);
        this.id = Integer.parseInt(parameter);
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.parkCar(id);
    }
}
