package command;

import application.ParkingLot;
import command.interfaces.SingleParameterCommandInterface;

import java.util.List;

public class ParkCarCommand implements SingleParameterCommandInterface {
    private final int id;

    public ParkCarCommand(List<String> parameterLineSplit) {
        this.id = this.extractValidSingleParameter(parameterLineSplit);
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.parkCar(id);
    }
}
