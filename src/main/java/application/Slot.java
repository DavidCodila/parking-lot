package application;

import application.interfaces.UnParkSlotFunction;
import com.google.common.annotations.VisibleForTesting;

public class Slot {
    private final int number;
    private Boolean isTaken;
    private UnParkSlotFunction unParkSlotFunction;

    public Slot(int number) {
        this.number = number;
        this.isTaken = false;
    }

    public void parkCar(Car car) {
        if (this.isTaken) {
            throw new RuntimeException("Can not park car in slot number: " + this.number);
        }
        this.isTaken = true;
        car.parkInSlot(this.number);
        car.setUnParkCarFunction(this::unParkCar);
    }

    private void unParkCar() {
        if (!this.isTaken) {
            throw new RuntimeException("There is no car in slot: " + this.number + " to un park.");
        }
        this.isTaken = false;
        this.unParkSlotFunction.execute(this);
        System.out.printf("Slot %d is free\n", this.number);
    }

    public void setUnParkSlotFunction(UnParkSlotFunction unParkSlotFunction) {
        this.unParkSlotFunction = unParkSlotFunction;
    }

    @VisibleForTesting
    void evokeUnParkCar() {
        this.unParkCar();
    }
}
