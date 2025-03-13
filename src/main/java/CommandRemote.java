public class CommandRemote {
    private final ParkingLot parkingLot;

    public CommandRemote(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void executeCommand(Command command) {
        command.execute(this.parkingLot);
    }
}
