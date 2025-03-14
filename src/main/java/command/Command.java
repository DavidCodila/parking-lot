package command;

import application.ParkingLot;

public interface Command {
    void execute(ParkingLot parkingLot);
}
