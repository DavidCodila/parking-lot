package command;

import application.ParkingLot;
import command.interfaces.CommandInterface;

import java.security.InvalidParameterException;
import java.util.List;

public class ListCarCommand implements CommandInterface {
    private final List<Integer> ids;

    public ListCarCommand(List<String> parameterLineSplit) {
        List<Integer> validParameters = parameterLineSplit.stream()
                .filter(this::isInteger)
                .map(Integer::valueOf)
                .toList();
        if (validParameters.isEmpty()) {
            throw new InvalidParameterException("Parameter " + parameterLineSplit + " is not valid");
        }
        this.ids = validParameters;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.listCars(this.ids);
    }
}
