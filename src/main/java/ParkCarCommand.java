public class ParkCarCommand implements Command {
    private final int id;

    public ParkCarCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.parkCar(id);
    }
}
