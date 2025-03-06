import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private int number;
    private int distanceFromEntry;
    private Car car;

    public Slot(int number, int distanceFromEntry) {
        this.number = number;
        this.distanceFromEntry = distanceFromEntry;
        this.car = null;
    }

    @VisibleForTesting
    boolean isTaken() {
        return this.car != null;
    }

    public void parkCar(Car car) {
        if (this.car == null) {
            this.car = car;
        }
        //need to add pass to next slot somehow
    }
}
