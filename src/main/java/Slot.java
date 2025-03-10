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
            this.car.parkInSlot(this.number);
        } else if (this.nextSlot != null) {
            this.nextSlot.parkCar(car);
        } else {
            throw new RuntimeException("Can not park car, the parking lot is full");
        }
    }

    public void getCar(int id) {
        String output = this.car.printCarIdSlotId(id);
        if (!output.isBlank()) {
            System.out.println(output);
        } else {
            this.nextSlot.getCar(id);
        }
    }

    @VisibleForTesting
    public int getNumber() {
        return this.number;
    }

    @VisibleForTesting
    Car getCar() {
        return this.car;
    }
}
