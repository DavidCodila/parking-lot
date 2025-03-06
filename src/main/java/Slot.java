import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private int number;
    private Car car;

    public Slot(int number) {
        this.number = number;
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

    public void unParkCar() {
        this.car = null;
    }
}
