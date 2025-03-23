package command.interfaces;

import application.ParkingLot;

public interface CommandInterface {
    void execute(ParkingLot parkingLot);

    default boolean isInteger(String parameter) {
        return parameter.chars().allMatch(Character::isDigit);
    }
}
