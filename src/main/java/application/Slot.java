package application;

import com.google.common.annotations.VisibleForTesting;

public class Slot implements Observer, Comparable<Slot> {
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
            this.car.setObserver(this);
        } else if (this.nextSlot != null) {
            this.nextSlot.parkCar(car);
        } else {
            throw new RuntimeException("Can not park car, the parking lot is full");
        }
    }

    @Override
    public void unParkCar() {
        this.car = null;
        System.out.printf("Slot %d is free\n", this.number);
    }

    @Override
    public int compareTo(Slot otherSlot) {
        return Integer.compare(this.distance, otherSlot.distance);
    }

    @VisibleForTesting
    Car getCar() {
        return this.car;
    }
}
