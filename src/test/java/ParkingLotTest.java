import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    private static final int MAX_CAPACITY = 5;
    private final int car1Id = 1;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY);
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
        for (int i = 0; i < MAX_CAPACITY; i++) {
            this.parkingLot.parkCar(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(MAX_CAPACITY));
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

    @Test
    public void testFindCar() throws Exception {
        this.parkingLot.parkCar(this.car1Id);
        this.parkingLot.findCar(this.car1Id);
        String methodOutput = tapSystemOut(() -> this.parkingLot.findCar(this.car1Id)).trim();
        assertEquals(
                this.car1Id + " is parked at Slot number " + this.parkingLot.getSlotAtIndex(0).getNumber(),
                methodOutput
        );
    }

    @Test
    public void testCanNotFindCar() {
        int notPresentId = this.car1Id + 1;
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.findCar(notPresentId)
        );
        assertEquals("Car with id: " + notPresentId + " could not be found", exception.getMessage());

    }
}
