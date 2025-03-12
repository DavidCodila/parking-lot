import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private final int number;
    private final int distance;
    private Car car = null;
    private Slot nextSlot = null;

    public Slot(int number, int distance) {
        this.number = number;
        this.distance = distance;
    }

    public void setNextSlot(Slot slot) {
        this.nextSlot = slot;
    }

    public void parkCar(Car car) {
        if (this.car == null) {
            this.car = car;
            this.car.parkInSlot(this.number);
        } else if (this.nextSlot != null) {
            this.nextSlot.parkCar(car);
        } else {
            throw new RuntimeException("Can not park car, the parking lot is full");
        }
    }

    public void unParkCar(Car car) {
        if (this.car == car) {
            this.car = null;
            System.out.printf("Slot %d is free\n", this.number);
        } else if (this.nextSlot != null) {
            this.nextSlot.unParkCar(car);
        } else {
            throw new RuntimeException("That car does not has a slot");
        }
    }

    @VisibleForTesting
    Car getCar() {
        return this.car;
    }
}
