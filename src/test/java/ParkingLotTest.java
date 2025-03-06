import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    private static final int MAX_CAPACITY = 2;
    private ParkingLot parkingLot;
    private Car car;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY, new BasicSlotGenerator());
        this.car = new Car(0);
    }

    @Test
    public void testParkCar() {
        this.parkingLot.parkCar(this.car);
        assertEquals(this.car, this.parkingLot.getSlotAtIndex(0).getCar());
    }

    @Test
    public void testParkTwoCars() {
        this.parkingLot.parkCar(this.car);
        Car car2 = new Car(2);
        this.parkingLot.parkCar(car2);
        assertEquals(car2, this.parkingLot.getSlotAtIndex(1).getCar());
    }

    @Test
    public void testTryToGetASlotRecordWithAIndexToLarge() {
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.getSlotAtIndex(MAX_CAPACITY));
        assertEquals("Index " + MAX_CAPACITY + " is to large.", exception.getMessage());
    }
}
