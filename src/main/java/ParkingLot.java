import com.google.common.annotations.VisibleForTesting;

import java.util.HashMap;
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
        //need to add maxCapacity check here
        Car car = new Car(id);
        this.slotRecord.addCar(car);
        this.carRecord.put(id, car);
    }

    public void findCar(int id) {
        Car car = this.carRecord.get(id);
        if (car == null) {
            throw new RuntimeException("Car with id: " + id + " could not be found");
        }
        car.printInformation();
    }

    //Need to find out what [PATTERN] is
    public void print() {
        if (this.carRecord.isEmpty()) {
            System.out.println("No cars in the Parking Lot");
        } else {
            for(Car car : this.carRecord.values()) {
                car.printInformation();
            }
        }
    }

    public void unParkCar(int id) {
        Car carToUnPark = this.carRecord.get(id);
        if (carToUnPark == null) {
            throw new RuntimeException("Can not un-park car with id: " + id);
        }
        this.slotRecord.removeCar(carToUnPark);
    }

    @VisibleForTesting
    Car getCarById(int id) {
        return this.carRecord.get(id);
    }
}
