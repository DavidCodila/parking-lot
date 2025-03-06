import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    static final int MAX_CAPACITY = 2;
    ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY, new BasicSlotGenerator());
    }

    @Test
    public void testParkCar() {
        Car car = new Car(0);
        this.parkingLot.parkCar(car);
        assertEquals(car, this.parkingLot.getSlotAtIndex(0).getCar());
    }

    @Test
    public void testTryToGetASlotRecordWithAIndexToLarge() {
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.getSlotAtIndex(MAX_CAPACITY));
        assertEquals("Index " + MAX_CAPACITY + " is to large.", exception.getMessage());
    }
}
