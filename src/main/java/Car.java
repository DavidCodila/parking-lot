import com.google.common.annotations.VisibleForTesting;

public class Car {
    private int id;
    private int slotNumber;

    public Car(int id) {
        this.id = id;
    }

    public void parkInSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @VisibleForTesting
    protected int getSlotNumber() {
        return this.slotNumber;
    }
}
