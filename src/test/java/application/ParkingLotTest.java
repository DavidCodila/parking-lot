package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    private static final int MAX_CAPACITY = 5;
    private static final int CAR_0_ID = 0;
    private static final int CAR_1_ID = 1;
    private static final int SLOT_0_ID = 0;
    private static final int SLOT_1_ID = 1;
    private static final int CAR_NOT_PRESENT_ID = -1;

    private ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY);
    }

    @Test
    public void testParkCar() throws Exception {
        String methodOutput = tapSystemOut(() -> this.parkingLot.parkCar(CAR_0_ID)).trim();
        assertEquals("SLOT " + SLOT_0_ID + " is allocated to " + CAR_0_ID, methodOutput);
        assertEquals(this.parkingLot.getSlotNumberFromCarId(CAR_0_ID), SLOT_0_ID);
    }

    @Test
    public void testParkCarInClosestSpot() {
        this.parkingLot.assignSlotNumberToCarId(CAR_1_ID, SLOT_0_ID);
        this.parkingLot.parkCar(CAR_0_ID);
        assertEquals(SLOT_1_ID, this.parkingLot.getSlotNumberFromCarId(CAR_0_ID));
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            this.parkingLot.parkCar(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(MAX_CAPACITY));
        assertEquals("Can not park car, parking lot is full\n", exception.getMessage());
    }

    @Test
    public void testFindCar() throws Exception {
        this.parkingLot.assignSlotNumberToCarId(CAR_0_ID, SLOT_0_ID);
        String methodOutput = tapSystemOut(() -> this.parkingLot.findCar(CAR_0_ID)).trim();
        assertEquals(CAR_0_ID + " is parked at SLOT number " + SLOT_0_ID, methodOutput);
    }

    @Test
    public void testCanNotFindCar() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.parkingLot.findCar(CAR_NOT_PRESENT_ID)
        );
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void testListCars() throws Exception {
        this.parkingLot.assignSlotNumberToCarId(CAR_0_ID, SLOT_0_ID);
        this.parkingLot.assignSlotNumberToCarId(CAR_1_ID, SLOT_1_ID);
        String methodOutput = tapSystemOut(() ->
                this.parkingLot.listCars(List.of(CAR_0_ID, CAR_1_ID)))
                .trim();
        String expected = CAR_0_ID + " is parked at SLOT number " + SLOT_0_ID + "\n"
                        + CAR_1_ID + " is parked at SLOT number " + SLOT_1_ID;
        assertEquals(expected, methodOutput);
    }

    @Test
    public void testListCarsWithWrongCarId() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.parkingLot.listCars(List.of(CAR_NOT_PRESENT_ID))
        );
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void testUnParkCar() throws Exception {
        this.parkingLot.assignSlotNumberToCarId(CAR_0_ID, SLOT_0_ID);
        String methodOutput = tapSystemOut(() -> this.parkingLot.unParkCar(CAR_0_ID)).trim();
        assertEquals("SLOT " + SLOT_0_ID + " is free", methodOutput);
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.parkingLot.getSlotNumberFromCarId(SLOT_0_ID));
        assertEquals("Car with id: " + CAR_0_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void TestUnParkACarThatIsNotInTheParkingLot() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.parkingLot.unParkCar(CAR_NOT_PRESENT_ID)
        );
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void TestUnParkACarFromAnInvalidSlot() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.parkingLot.unParkCar(CAR_NOT_PRESENT_ID)
        );
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }
}
