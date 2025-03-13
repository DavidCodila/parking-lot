import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class SlotRecord {
    private List<Slot> slots = new ArrayList<>();

    public SlotRecord(SlotListGenerator slotListGenerator) {
        List<Slot> orderedSlots = slotListGenerator.generate().stream()
                .sorted(Slot::compareTo)
                .toList();
        this.slots = generateChainOfResponsibilityListOfSlots(orderedSlots);
    }

    private List<Slot> generateChainOfResponsibilityListOfSlots(List<Slot> slots) {
        int size = slots.size();
        List<Slot> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(slots.get(i));
            if(i > 0) {
                result.get(i - 1).setNextSlot(result.get(i));
            }
        }
        return result;
    }

    public void addCar(Car car) {
        this.slots.getFirst().parkCar(car);
    }

    @VisibleForTesting
    Car getCarAtIndex(int i) throws IndexOutOfBoundsException {
        return this.slots.get(i).getCar();
    }
}
