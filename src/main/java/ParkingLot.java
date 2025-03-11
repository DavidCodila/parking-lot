import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int MAX_CAPACITY = 5;
    private final List<Slot> slotRecord = new ArrayList<>(this.MAX_CAPACITY);

    public ParkingLot() {
        for (int i = 0; i < this.MAX_CAPACITY; i++) {
            this.slotRecord.add(new Slot(i, i+1));
            if(i > 0) {
                this.slotRecord.get(i - 1).setNextSlot(this.slotRecord.get(i));
            }
        }
    }

    public void parkCar(int id) {
        this.slotRecord.getFirst().parkCar(new Car(id));
    }

    public void findCar(int id) {}

    @VisibleForTesting
    int getMaxCapacity() {
        return this.MAX_CAPACITY;
    }

    @VisibleForTesting
    Slot getSlotAtIndex(int i) throws IndexOutOfBoundsException {
        return this.slotRecord.get(i);
    }
}
