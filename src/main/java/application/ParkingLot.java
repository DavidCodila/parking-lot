package application;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final Map<Integer, Integer> carIDToSlotNumberMap = new HashMap<>();
    private final List<Integer> slotNumbers;

    public ParkingLot(int maxCapacity) {
        this.slotNumbers = new ArrayList<>(maxCapacity);
        for (int i = 0; i < maxCapacity; i++) {
            this.slotNumbers.add(i);
        }
    }

    public void parkCar(int id) {
        int slotNumber = this.slotNumbers.stream()
                .min(Integer::compareTo)
                .orElseThrow(() ->
                        new RuntimeException("Can not park car, parking lot is full")
                );
        System.out.printf("SLOT %d is allocated to %d\n", slotNumber, id);

        this.slotNumbers.removeIf(value -> value.equals(slotNumber));
        this.carIDToSlotNumberMap.put(id, slotNumber);
    }

    public void findCar(int id) {
        this.validateCarIsInParkingLot(id);
        System.out.printf("%d is parked at Slot number %d\n", id, this.carIDToSlotNumberMap.get(id));
    }

    public void listCars(List<Integer> ids) {
        for(Integer id : ids) {
            this.findCar(id);
        }
    }

    public void unParkCar(int id) {
        this.validateCarIsInParkingLot(id);
        int slotNumber = this.carIDToSlotNumberMap.get(id);
        this.slotNumbers.add(slotNumber);
        System.out.printf("Slot %d is free\n", slotNumber);
        this.carIDToSlotNumberMap.remove(id);
    }

    private void validateCarIsInParkingLot(int id) {
        if (!this.carIDToSlotNumberMap.containsKey(id)) {
            throw new RuntimeException("Car with id: " + id + " could not be found");
        }
    }

    @VisibleForTesting
    Integer getCarIDToSlotNumberMap(int id) {
        return carIDToSlotNumberMap.get(id);
    }

    @VisibleForTesting
    List<Integer> getSlotNumbers() {
        return slotNumbers;
    }

}
