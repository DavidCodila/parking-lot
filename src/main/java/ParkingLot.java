import com.google.common.annotations.VisibleForTesting;

import java.util.List;

public class ParkingLot {
    private final int maxCapacity;
    private List<Slot> slotRecord;

    public ParkingLot(int maxCapacity, SlotGenerator slotGenerator) {
        this.maxCapacity = maxCapacity;
        this.slotRecord = slotGenerator.generateSlots(this.maxCapacity)
                .stream()
                .sorted(Slot::compareTo)
                .toList();
        for (int i = 0; i < this.maxCapacity - 1; i++) {
            Slot nextSlot = this.slotRecord.get(i + 1);
            this.slotRecord.get(i).setNextSlot(nextSlot);
        }
    }

    public void parkCar(Car car) {
        this.slotRecord.getFirst().parkCar(car);
    }

    @VisibleForTesting
    Slot getSlotAtIndex(int i) throws RuntimeException {
        if (i >= slotRecord.size()) {
            throw new RuntimeException("Index " + i + " is to large.");
        }
        return slotRecord.get(i);
    }

}
