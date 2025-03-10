import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//TODO: Make into integration test class then make another
// class for ParkingLotTest with mocked dependencies
public class ParkingLotTest {
    private int maxCapacity;
    private ParkingLot parkingLot;
    private final int car1Id = 1;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot();
        this.maxCapacity = this.parkingLot.getMaxCapacity();
    }

    @Test
    public void testParkCar() {
        this.parkingLot.parkCar(this.car1Id);
        assertEquals(this.car1Id, this.parkingLot.getSlotAtIndex(0).getCar().getId());
    }

    @Test
    public void testParkTwoCars() {
        int car2Id = 2;
        this.parkingLot.parkCar(this.car1Id);
        this.parkingLot.parkCar(car2Id);
        assertEquals(car2Id, this.parkingLot.getSlotAtIndex(1).getCar().getId());
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = 0; i < this.maxCapacity; i++) {
            this.parkingLot.parkCar(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(this.maxCapacity));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        int car3Id = 3;
        this.parkingLot.getSlotAtIndex(0).parkCar(new Car(1));
        this.parkingLot.getSlotAtIndex(1).parkCar(new Car(2));
        this.parkingLot.parkCar(car3Id);
        assertEquals(car3Id, this.parkingLot.getSlotAtIndex(2).getCar().getId());
    }
}
