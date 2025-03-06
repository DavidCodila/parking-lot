import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    private static final int MAX_CAPACITY = 5;
    private ParkingLot parkingLot;
    private Car car0;
    private Car car1;
    private Car car2;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY, new BasicSlotGenerator());
        this.car0 = new Car(0);
        this.car1 = new Car(1);
        this.car2 = new Car(2);
    }

    @Test
    public void testParkCar() {
        this.parkingLot.parkCar(this.car0);
        assertEquals(this.car0, this.parkingLot.getSlotAtIndex(0).getCar());
    }

    @Test
    public void testParkTwoCars() {
        this.parkingLot.parkCar(this.car0);
        this.parkingLot.parkCar(this.car1);
        assertEquals(this.car0, this.parkingLot.getSlotAtIndex(0).getCar());
        assertEquals(this.car1, this.parkingLot.getSlotAtIndex(1).getCar());
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            this.parkingLot.parkCar(new Car(i));
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(new Car(MAX_CAPACITY)));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        this.parkingLot.getSlotAtIndex(0).parkCar(this.car0);
        this.parkingLot.getSlotAtIndex(1).parkCar(this.car1);
        this.parkingLot.parkCar(this.car2);
        assertEquals(this.car2, this.parkingLot.getSlotAtIndex(2).getCar());
    }
}
