import com.google.common.annotations.VisibleForTesting;

import java.util.List;

public class ParkingLot {
    private final int maxCapacity;
    private List<Slot> slotRecord;

    public ParkingLot(int maxCapacity, SlotGenerator slotGenerator) {
        this.maxCapacity = maxCapacity;
        this.slotRecord = orderSlotsByDistanceAscending(slotGenerator.generateSlots(this.maxCapacity));
        for (int i = 0; i < this.maxCapacity - 1; i++) {
            Slot nextSlot = this.slotRecord.get(i + 1);
            this.slotRecord.get(i).setNextSlot(nextSlot);
        }
    }

    private List<Slot> orderSlotsByDistanceAscending(List<Slot> slots) {
        return slots.stream()
                .sorted(Slot::compareTo)
                .toList();
    }

    public void parkCar(Car car) {
        this.slotRecord.getFirst().parkCar(car);
    }

    @VisibleForTesting
    protected Slot getSlotAtIndex(int i) throws IndexOutOfBoundsException {
        return slotRecord.get(i);
    }
}
