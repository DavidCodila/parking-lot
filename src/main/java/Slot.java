import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private int number;
    private boolean isTaken;
    private int distanceFromEntry;

    public Slot(int number, int distanceFromEntry) {
        this.number = number;
        this.distanceFromEntry = distanceFromEntry;
        this.isTaken = false;
    }

    @VisibleForTesting
    boolean isTaken() {
        return isTaken;
    }

    public void parkCar() {
        this.isTaken = true;
    }
}
