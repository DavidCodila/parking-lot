import java.util.List;

public class ListCarCommand implements Command {
    private final List<Integer> ids;

    public ListCarCommand(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.print(ids);
    }
}
