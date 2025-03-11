import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    private static final int MAX_CAPACITY = 5;
    private final int car1Id = 1;
    private final int car2Id = 2;

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
        this.parkingLot.parkCar(this.car1Id);
        this.parkingLot.parkCar(this.car2Id);
        assertEquals(this.car2Id, this.parkingLot.getSlotAtIndex(1).getCar().getId());
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

    @Test
    public void testPrint() throws Exception {
        this.parkingLot.parkCar(this.car1Id);
        this.parkingLot.parkCar(this.car2Id);
        String methodOutput = tapSystemOut(() -> this.parkingLot.print()).trim();
        assertEquals(
                this.car1Id + " is parked at Slot number " + this.parkingLot.getSlotAtIndex(0).getNumber()
                + "\n" + this.car2Id + " is parked at Slot number " + this.parkingLot.getSlotAtIndex(1).getNumber(),
                methodOutput
        );
    }

    @Test
    public void testPrintWithNoCarsInParkingLot() throws Exception {
        String methodOutput = tapSystemOut(() -> this.parkingLot.print()).trim();
        assertEquals("No cars in the Parking Lot", methodOutput);
    }
}
