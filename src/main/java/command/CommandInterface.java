package command;

import application.ParkingLot;

public interface CommandInterface {
    void execute(ParkingLot parkingLot);
}
