import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final List<Slot> slotRecord = new ArrayList<>();
    private final Map<Integer, Car> carRecord = new HashMap<>();

    //need to think about altering distance assignment
    public ParkingLot(int maxCapacity) {
        for (int i = 0; i < maxCapacity; i++) {
            this.slotRecord.add(new Slot(i, i+1));
            if(i > 0) {
                this.slotRecord.get(i - 1).setNextSlot(this.slotRecord.get(i));
            }
        }
    }

    public void parkCar(int id) {
        Car car = new Car(id);
        this.slotRecord.getFirst().parkCar(car);
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

    @VisibleForTesting
    Slot getSlotAtIndex(int i) throws IndexOutOfBoundsException {
        return this.slotRecord.get(i);
    }
}
