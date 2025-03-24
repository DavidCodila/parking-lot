package command;

import application.ParkingLot;
import command.interfaces.SingleParameterCommandInterface;

import java.util.List;

public class UnParkCarCommand implements SingleParameterCommandInterface {
    private final int id;

    public UnParkCarCommand(List<String> parameterLineSplit) {
        this.id = this.extractValidSingleParameter(parameterLineSplit);
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.unParkCar(id);
    }
}
