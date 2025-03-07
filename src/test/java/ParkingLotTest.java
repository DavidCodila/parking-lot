import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    private int maxCapacity;
    private ParkingLot parkingLot;
    private List<Car> cars;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot();
        this.maxCapacity = this.parkingLot.MAX_CAPACITY;
        this.cars = new ArrayList<>() {{
            add(new Car(0));
            add(new Car(1));
            add(new Car(2));
        } };
    }

    @Test
    public void testParkCar() {
        this.parkingLot.parkCar(this.cars.getFirst());
        assertEquals(this.cars.getFirst(), this.parkingLot.getSlotAtIndex(0).getCar());
    }

    @Test
    public void testParkTwoCars() {
        this.parkingLot.parkCar(this.cars.getFirst());
        this.parkingLot.parkCar(this.cars.get(1));
        assertEquals(this.cars.get(1), this.parkingLot.getSlotAtIndex(1).getCar());
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = 0; i < this.maxCapacity; i++) {
            this.parkingLot.parkCar(new Car(i));
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(new Car(this.maxCapacity)));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        this.parkingLot.getSlotAtIndex(0).parkCar(this.cars.getFirst());
        this.parkingLot.getSlotAtIndex(1).parkCar(this.cars.get(1));
        this.parkingLot.parkCar(this.cars.get(2));
        assertEquals(this.cars.get(2), this.parkingLot.getSlotAtIndex(2).getCar());
    }
}
