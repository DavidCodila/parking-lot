package application;

import com.google.common.annotations.VisibleForTesting;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final Map<Integer, Integer> slotNumberToCarIdMap;

    public ParkingLot(int maxCapacity) {
        this.slotNumberToCarIdMap = new HashMap<>(maxCapacity);
        for (int i = 0; i < maxCapacity; i++) {
            this.slotNumberToCarIdMap.put(i, null);
        }
    }

    public void parkCar(int carId) {
        Integer slotNumber = this.slotNumberToCarIdMap.entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("Can not park car, parking lot is full"));
        this.slotNumberToCarIdMap.put(slotNumber, carId);
        System.out.printf("SLOT %d is allocated to %d\n", slotNumber, carId);
    }

    public void findCar(int carId) {
        System.out.printf("%d is parked at Slot number %d\n", carId, this.getSlotNumberFromCarID(carId));
    }

    public void listCars(List<Integer> carIds) {
        carIds.forEach(this::findCar);
    }

    public void unParkCar(int carId) {
        int slotNumber = this.getSlotNumberFromCarID(carId);
        this.slotNumberToCarIdMap.put(slotNumber, null);
        System.out.printf("Slot %d is free\n", slotNumber);
    }

    private int getSlotNumberFromCarID(int carId) {
        return this.slotNumberToCarIdMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue() == carId)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException("Car with id: " + carId + " could not be found"));
    }

    @VisibleForTesting
    Map.Entry <Integer, Integer> retrieveSlotNumberToCarIdEntry(int carId) {
        return this.slotNumberToCarIdMap.entrySet().stream()
                .filter(entry -> entry.getKey() == carId)
                .findFirst()
                .orElseThrow( () -> new NullPointerException("Car with id: " + carId + " could not be found"));
    }

    @VisibleForTesting
    void setCarIDToSlotNumber(int carId, int slotNumber) {
        this.slotNumberToCarIdMap.put(slotNumber, carId);
    }
}
