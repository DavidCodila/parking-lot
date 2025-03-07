import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private int number;
    private Car car;
    private final int distance;
    private Slot nextSlot;

    public Slot(int number, int distance) {
        this.number = number;
        this.distance = distance;
        this.car = null;
    }

    public void setNextSlot(Slot slot) {
        this.nextSlot = slot;
    }

    public void parkCar(Car car) {
        if (this.car == null) {
            this.car = car;
        } else if (this.nextSlot == null) {
            throw new RuntimeException("Can not park car, the parking lot is full");
        } else {
            this.nextSlot.parkCar(car);
        }
    }

    @VisibleForTesting
    protected Car getCar() {
        return this.car;
    }
}
