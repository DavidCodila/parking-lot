import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SlotRecordTest {
    private static final int MAX_CAPACITY = 5;
    private static final int ZER0TH_INDEX = 0;
    private static final int FIRST_INDEX = 1;

    private SlotRecord slotRecord;
    @Mock private Car car1;
    @Mock private Car car2;


    @BeforeEach
    public void setUp() {
        int carId = 1;
        int car2Id = 2;

        this.slotRecord = new SlotRecord(MAX_CAPACITY);
        this.car1 = new Car(carId);
        this.car2 = new Car(car2Id);

        this.slotRecord.addCar(this.car1);
    }

    @Test
    public void testParkCar() {
        assertEquals(this.car1, this.slotRecord.getCarAtIndex(ZER0TH_INDEX));
    }

    @Test
    public void testParkTwoCars() {
        this.slotRecord.addCar(this.car2);
        assertEquals(this.car2, this.slotRecord.getCarAtIndex(FIRST_INDEX));
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = FIRST_INDEX; i < MAX_CAPACITY; i++) {
            this.slotRecord.addCar(new Car(i));
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.slotRecord.addCar(new Car(MAX_CAPACITY)));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        int car3Id = 3;
        final int SECOND_INDEX = 2;
        Car car3 = new Car(car3Id);
        this.slotRecord.addCar(this.car2);
        this.slotRecord.addCar(car3);
        assertEquals(car3, this.slotRecord.getCarAtIndex(SECOND_INDEX));
    }

    @Test
    public void testUnParkCar() {
        this.slotRecord.removeCar(this.car1);
        assertNull(this.slotRecord.getCarAtIndex(ZER0TH_INDEX));
    }

    @Test
    public void testUnParkSecondCarInParkingLot() {
        this.slotRecord.addCar(this.car2);
        this.slotRecord.removeCar(this.car2);
        assertNull(this.slotRecord.getCarAtIndex(FIRST_INDEX));
    }

    @Test
    public void testUnParkManyCars() {
        this.slotRecord.addCar(this.car2);

        this.slotRecord.removeCar(this.car1);
        this.slotRecord.removeCar(this.car2);

        assertNull(this.slotRecord.getCarAtIndex(ZER0TH_INDEX));
        assertNull(this.slotRecord.getCarAtIndex(FIRST_INDEX));
    }
}
