package command;

import application.ParkingLot;
import command.interfaces.SingleParameterCommandInterface;

import java.util.List;

public class FindCarCommand implements SingleParameterCommandInterface {
    private final int id;

    public FindCarCommand(List<String> parameterLineSplit) {
        this.validateIsSingleParameterCommand(parameterLineSplit);
        this.id = Integer.parseInt(parameterLineSplit.getFirst());
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.findCar(id);
    }
}
