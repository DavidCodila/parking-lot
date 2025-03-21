package command;

import application.ParkingLot;

import java.security.InvalidParameterException;

public interface CommandInterface {
    void execute(ParkingLot parkingLot);

    default void validateIsInteger(String parameter) {
        try {
            Integer.parseInt(parameter);
        } catch (Exception e) {
            throw new InvalidParameterException("Parameter " + parameter + " is not valid");
        }
    }
}
