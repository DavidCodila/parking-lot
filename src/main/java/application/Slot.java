package application;

import application.interfaces.UnParkSlotFunction;
import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private final int number;
    private Boolean taken;
    private UnParkSlotFunction unParkSlotFunction;

    public Slot(int number) {
        this.number = number;
        this.taken = false;
    }

    public void setUnParkSlotFunction(UnParkSlotFunction unParkSlotFunction) {
        this.unParkSlotFunction = unParkSlotFunction;
    }

    public void parkCar(Car car) {
        if (this.taken) {
            throw new RuntimeException("Can not park car in slot number: " + this.number);
        }
        this.taken = true;
        car.parkInSlot(this.number);
        car.setUnParkCarFunction(this::unParkCar);
    }

    private void unParkCar() {
        if (!this.taken) {
            throw new RuntimeException("There is no car in slot: " + this.number + " to un park.");
        }
        this.taken = false;
        this.unParkSlotFunction.execute(this);
        System.out.printf("Slot %d is free\n", this.number);
    }

    @VisibleForTesting
    void evokeUnParkCar() {
        this.unParkCar();
    }
}
