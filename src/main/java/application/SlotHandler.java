package application;

import com.google.common.annotations.VisibleForTesting;

import java.util.*;

import static java.util.Map.Entry;

public class SlotHandler {
    private final Map<Integer, Slot> vacantSlotRegister = new Hashtable<>();
    private final Map<Integer, Slot> occupiedSlotRegister = new Hashtable<>();

    public SlotHandler(SlotRecordGenerator slotListGenerator) {
        List<Slot> orderedSlots = slotListGenerator.generate().stream()
                .sorted(Comparator.comparingInt(SlotRecord::distance))
                .map(SlotRecord::slot)
                .toList();
        orderedSlots.forEach(slot -> slot.setUnParkFromSlotFunction(this::onUnParkCar));
        for (int i = 0; i < orderedSlots.size(); i++) {
            this.vacantSlotRegister.put(i, orderedSlots.get(i));
        }
    }

    public void parkCar(Car car) {
        Optional<Entry<Integer, Slot>> closestSlotEntry = this.vacantSlotRegister.entrySet()
                .stream()
                .min(Entry.comparingByKey());
        if (closestSlotEntry.isPresent()) {
            Slot slot = closestSlotEntry.get().getValue();
            Integer key = closestSlotEntry.get().getKey();
            slot.parkCar(car);
            this.occupiedSlotRegister.put(key, slot);
            this.vacantSlotRegister.remove(key);
        } else {
            throw new RuntimeException("Can not park car, the parking lot is full");
        }
    }

    public void onUnParkCar(Slot slot) {
        Entry<Integer, Slot> slotEntryToUnPark = this.occupiedSlotRegister.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(slot))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error with onUnParkCar method call"));
        this.vacantSlotRegister.put(slotEntryToUnPark.getKey(), slotEntryToUnPark.getValue());
        this.occupiedSlotRegister.remove(slotEntryToUnPark.getKey());
    }

    @VisibleForTesting
    Car getCarAtIndex(int i) throws IndexOutOfBoundsException {
        return this.occupiedSlotRegister.get(i).getCar();
    }

    @VisibleForTesting
    Slot getSlotAtIndex(int i) throws IndexOutOfBoundsException {
        return this.occupiedSlotRegister.get(i);
    }

    @VisibleForTesting
    void setCarAtIndex(int i, Car car) throws IndexOutOfBoundsException {
        Slot slot = this.vacantSlotRegister.get(i);
        slot.parkCar(car);
        this.occupiedSlotRegister.put(i, slot);
        this.vacantSlotRegister.remove(i);
    }
}
