package command.interfaces;

import application.ParkingLot;

import java.security.InvalidParameterException;
import java.util.List;

public interface CommandInterface {
    void execute(ParkingLot parkingLot);

    default List<Integer> extractValidParameterList(List<String> parameterLineSplit) {
        List<Integer> validParameters = parameterLineSplit.stream()
                .filter(parameter -> parameter.chars().allMatch(Character::isDigit))
                .map(Integer::valueOf)
                .toList();
        if (validParameters.isEmpty()) {
            throw new InvalidParameterException("Parameter " + parameterLineSplit + " is not valid");
        }
        return validParameters;
    }
}
