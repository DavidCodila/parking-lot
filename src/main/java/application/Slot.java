package application;

import application.interfaces.UnParkFromSlotFunctionInterface;
import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private final int number;
    private Car car = null;
    private UnParkFromSlotFunctionInterface unParkFromSlotFunction;

    public Slot(int number) {
        this.number = number;
    }

    public void setUnParkFromSlotFunction(UnParkFromSlotFunctionInterface unParkFromSlotFunction) {
        this.unParkFromSlotFunction = unParkFromSlotFunction;
    }

    public void parkCar(Car car) {
        if (this.car == null) {
            this.car = car;
            this.car.parkInSlot(this.number);
            this.car.setUnParkCarFunction(this::unParkCar);
        } else {
            throw new RuntimeException("Can not park car in slot number: " + this.number);
        }
    }

    public void unParkCar() {
        this.car = null;
        this.unParkFromSlotFunction.execute(this);
        System.out.printf("Slot %d is free\n", this.number);
    }

    @VisibleForTesting
    Car getCar() {
        return this.car;
    }
}
