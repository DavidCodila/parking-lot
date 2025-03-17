package application;

import com.google.common.annotations.VisibleForTesting;

public class Slot implements CarObserver {
    private final int number;
    private Car car = null;
    public SlotObserver slotObserver;

    public Slot(int number) {
        this.number = number;
    }

    public void setSlotObserver(SlotObserver slotObserver) {
        this.slotObserver = slotObserver;
    }

    public void parkCar(Car car) {
        if (this.car == null) {
            this.car = car;
            this.car.parkInSlot(this.number);
            this.car.setCarObserver(this);
        } else {
            throw new RuntimeException("Can not park car in slot number: " + this.number);
        }
    }

    @Override
    public void unParkCar() {
        this.car = null;
        this.slotObserver.onUnParkCar(this);
        System.out.printf("Slot %d is free\n", this.number);
    }

    @VisibleForTesting
    Car getCar() {
        return this.car;
    }
}
