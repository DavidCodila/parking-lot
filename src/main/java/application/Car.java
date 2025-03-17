package application;

public class Car {
    private final int id;
    private int slotNumber;
    private CarObserver carObserver = null;

    public Car(int id) {
        this.id = id;
    }

    public void parkInSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        System.out.printf("SLOT %d is allocated to %d\n", this.slotNumber, this.id);
    }

    public void printInformation() {
        System.out.printf("%d is parked at Slot number %d\n", this.id, this.slotNumber);
    }

    public void unPark() {
        this.carObserver.unParkCar();
        this.carObserver = null;
    }

    public void setCarObserver(CarObserver carObserver) {
        this.carObserver = carObserver;
    }
}

