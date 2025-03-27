package application;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.IntStream;

public class ParkingLot {
    private final TreeSet<Integer> slots = new TreeSet<>(Integer::compareTo);
    private final Map<Integer, Integer> carRecord = new HashMap<>();

    public ParkingLot(int maxCapacity) {
            this.slots.addAll(
                    IntStream.range(0, maxCapacity)
                            .boxed()
                            .toList()
            );
    }

    public void parkCar(Integer carId) {
        Integer slotNumber = this.slots.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can not park car, parking lot is full\n"));
        this.assignSlotNumberToCarId(carId, slotNumber);
        System.out.printf("SLOT %d is allocated to %d\n", slotNumber, carId);
    }

    public void findCar(Integer carId) {
        System.out.printf("%d is parked at SLOT number %d\n", carId, this.getSlotNumberFromCarId(carId));
    }

    public void listCars(List<Integer> carIds) {
        carIds.forEach(this::findCar);
    }

    public void unParkCar(Integer carId) {
        Integer slotNumber = this.getSlotNumberFromCarId(carId);
        this.carRecord.remove(carId);
        this.slots.add(slotNumber);
        System.out.printf("SLOT %d is free\n", slotNumber);
    }

    //was extracted to method, because it was used by many tests
    void assignSlotNumberToCarId(Integer carId, Integer slotNumber) {
        this.carRecord.put(carId, slotNumber);
        this.slots.removeIf(number -> number.equals(slotNumber));
    }

    Integer getSlotNumberFromCarId(Integer carId) throws InvalidParameterException {
        Integer slotNumber = this.carRecord.get(carId);
        if (slotNumber == null) {
            throw new InvalidParameterException("Car with id: " + carId + " could not be found");
        }
        return slotNumber;
    }
}
