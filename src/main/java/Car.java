import com.google.common.annotations.VisibleForTesting;

public class Car {
    private int id;
    private int slotNumber;

    public Car(int id) {
        this.id = id;
    }

    public void parkInSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        System.out.printf("SLOT %d is allocated to %d\n", this.slotNumber, this.id);
    }

    public String printCarIdSlotId(int id) {
        return (this.id == id)
                ? this.id + " is parked at Slot number " + this.slotNumber
                : "";
    }

    @VisibleForTesting
    int getId() {
        return id;
    }

    @VisibleForTesting
    int getSlotNumber() {
        return this.slotNumber;
    }
}
