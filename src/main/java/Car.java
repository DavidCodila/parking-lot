import com.google.common.annotations.VisibleForTesting;

public class Car {
    private final int id;
    private int slotNumber;

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

    @VisibleForTesting
    int getId() {
        return this.id;
    }
}
