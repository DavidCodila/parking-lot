package application;

import com.google.common.annotations.VisibleForTesting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final int MAX_CAPACITY;
    private final Map<Integer, Car> carRecord = new HashMap<>();
    private final SlotHandler slotHandler;

    public ParkingLot(int maxCapacity, SlotHandler slotHandler) {
        this.MAX_CAPACITY = maxCapacity;
        this.slotHandler = slotHandler;
    }

    public void parkCar(int id) {
        if (this.carRecord.size() >= this.MAX_CAPACITY) {
            throw new RuntimeException("Can not park car, parking lot is full");
        }
        Car car = new Car(id);
        this.carRecord.put(id, car);
        Slot slot = this.slotHandler.getNextVacantSlot();
        slot.parkCar(car);
    }

    public void findCar(int id) {
        this.validateCarRecordContainsKey(id);
        this.carRecord.get(id).printInformation();
    }

    public void listCars(List<Integer> ids) {
        if (this.carRecord.isEmpty()) {
            System.out.println("No cars in the Parking Lot");
        } else {
            for(Integer id : ids) {
                this.findCar(id);
            }
        }
    }

    public void unParkCar(int id) {
        this.validateCarRecordContainsKey(id);
        this.carRecord.get(id).unPark();
        this.carRecord.remove(id);
    }

    private void validateCarRecordContainsKey(int id) {
        if (!this.carRecord.containsKey(id)) {
            throw new RuntimeException("Car with id: " + id + " could not be found");
        }
    }

    @VisibleForTesting
    void addCarToCarRecord(int id, Car car) {
        this.carRecord.put(id, car);
    }

    @VisibleForTesting
    Car getCarFromCarRecordById(Integer id) {
        return this.carRecord.get(id);
    }
}
