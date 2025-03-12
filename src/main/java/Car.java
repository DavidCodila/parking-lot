public class Car {
    private final int id;
    private int slotNumber;
    private Observer observer = null;

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
        this.observer.unParkCar();
        this.observer = null;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}
