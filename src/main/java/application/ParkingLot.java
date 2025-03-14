package application;

import com.google.common.annotations.VisibleForTesting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final int MAX_CAPACITY;
    private final Map<Integer, Car> carRecord = new HashMap<>();
    private final SlotRecord slotRecord;

    public ParkingLot(int maxCapacity, SlotRecord slotRecord) {
        this.MAX_CAPACITY = maxCapacity;
        this.slotRecord = slotRecord;
    }

    public void parkCar(int id) {
        if (this.carRecord.size() < this.MAX_CAPACITY) {
            Car car = new Car(id);
            this.slotRecord.addCar(car);
            this.carRecord.put(id, car);
        } else {
            throw new RuntimeException("Can not park car, parking lot is full");
        }
    }

    public void findCar(int id) {
        Car car = this.carRecord.get(id);
        if (car == null) {
            throw new RuntimeException("Car with id: " + id + " could not be found");
        }
        car.printInformation();
    }

    public void listCars(List<Integer> carIds) {
        if (this.carRecord.isEmpty()) {
            System.out.println("No cars in the Parking Lot");
        } else {
            for(Integer id : carIds) {
                this.findCar(id);
            }
        }
    }

    public void unParkCar(int id) {
        Car car = this.carRecord.get(id);
        if (car == null) {
            throw new RuntimeException("Can not un-park car with id: " + id);
        }
        car.unPark();
        this.carRecord.remove(id);
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
