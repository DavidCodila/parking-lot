package application;

import application.interfaces.SlotRecordGenerator;
import com.google.common.annotations.VisibleForTesting;

import java.util.*;

import static java.util.Map.Entry;

public class SlotHandler {
    private final Map<Integer, Slot> vacantSlotRegister = new HashMap<>();
    private final Map<Integer, Slot> occupiedSlotRegister = new HashMap<>();

    public SlotHandler(SlotRecordGenerator slotRecordGenerator) {
        List<Slot> orderedSlots = slotRecordGenerator.generate().stream()
                .sorted(Comparator.comparingInt(SlotRecord::distance))
                .map(SlotRecord::slot)
                .toList();
        orderedSlots.forEach(slot -> slot.setUnParkSlotFunction(this::onUnParkCar));
        for (int i = 0; i < orderedSlots.size(); i++) {
            this.vacantSlotRegister.put(i, orderedSlots.get(i));
        }
    }

    public Slot getNextVacantSlot() {
        Optional<Entry<Integer, Slot>> closestSlotEntry = this.vacantSlotRegister.entrySet()
                .stream()
                .min(Entry.comparingByKey());
        if (closestSlotEntry.isEmpty()) {
            throw new RuntimeException("Can not park car, the vacantSlotRegister is empty");
        }
        Slot slot = closestSlotEntry.get().getValue();
        Integer key = closestSlotEntry.get().getKey();
        this.occupiedSlotRegister.put(key, slot);
        this.vacantSlotRegister.remove(key);
        return slot;
    }

    private void onUnParkCar(Slot slot) {
        Entry<Integer, Slot> slotEntryToUnPark = this.occupiedSlotRegister.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(slot))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error with onUnParkCar method call"));
        this.vacantSlotRegister.put(slotEntryToUnPark.getKey(), slotEntryToUnPark.getValue());
        this.occupiedSlotRegister.remove(slotEntryToUnPark.getKey());
    }

    @VisibleForTesting
    Slot getOccupiedSlotAtIndex(int i) throws IndexOutOfBoundsException {
        return this.occupiedSlotRegister.get(i);
    }

    @VisibleForTesting
    Slot getVacantSlotAtIndex(int i) throws IndexOutOfBoundsException {
        return this.vacantSlotRegister.get(i);
    }

    @VisibleForTesting
    void setSlotAsOccupied(int i) throws IndexOutOfBoundsException {
        Slot slot = this.vacantSlotRegister.get(i);
        this.occupiedSlotRegister.put(i, slot);
        this.vacantSlotRegister.remove(i);
    }

    @VisibleForTesting
    void setOnUnParkCar(Slot slot) {
        this.onUnParkCar(slot);
    }
}
