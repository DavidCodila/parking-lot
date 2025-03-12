import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class SlotRecord {
    private final List<Slot> slots = new ArrayList<>();

    //need to think about altering distance assignment
    public SlotRecord(int numberOfSlots) {
        for (int i = 0; i < numberOfSlots; i++) {
            this.slots.add(new Slot(i, i+1));
            if(i > 0) {
                this.slots.get(i - 1).setNextSlot(this.slots.get(i));
            }
        }
    }

    public void addCar(Car car) {
        this.slots.getFirst().parkCar(car);
    }

    public void removeCar(Car carToUnPark) {
        this.slots.getFirst().unParkCar(carToUnPark);
    }

    @VisibleForTesting
    Car getCarAtIndex(int i) throws IndexOutOfBoundsException {
        return this.slots.get(i).getCar();
    }
}
