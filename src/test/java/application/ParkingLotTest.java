package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private static final int MAX_CAPACITY = 5;
    private static final int CAR_1_ID = 0;
    private static final int CAR_2_ID = 1;
    private static final int SLOT_1_ID = 0;
    private static final int SLOT_2_ID = 1;
    private static final int CAR_NOT_PRESENT_ID = -1;
    
    private ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY);
    }

    @Test
    public void testParkCar() throws Exception {
        String methodOutput = tapSystemOut(() -> this.parkingLot.parkCar(CAR_1_ID)).trim();
        assertEquals("SLOT " + SLOT_1_ID + " is allocated to " + CAR_1_ID, methodOutput);
        assertEquals(SLOT_1_ID, this.parkingLot.getCarIDToSlotNumberMap(CAR_1_ID));
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            this.parkingLot.parkCar(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(MAX_CAPACITY));
        assertEquals("Can not park car, parking lot is full", exception.getMessage());
    }

    @Test
    public void testFindCar() throws Exception {
        this.parkingLot.setCarIDToSlotNumber(CAR_1_ID, SLOT_1_ID);
        String methodOutput = tapSystemOut(() -> this.parkingLot.findCar(CAR_1_ID)).trim();
        assertEquals(CAR_1_ID + " is parked at Slot number " + SLOT_1_ID, methodOutput);
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
        this.parkingLot.setCarIDToSlotNumber(CAR_1_ID, SLOT_1_ID);
        this.parkingLot.setCarIDToSlotNumber(CAR_2_ID, SLOT_2_ID);
        String methodOutput = tapSystemOut(() ->
                this.parkingLot.listCars(List.of(CAR_1_ID, CAR_2_ID)))
                .trim();
        String expected = CAR_1_ID + " is parked at Slot number " + SLOT_1_ID + "\n"
                        + CAR_2_ID + " is parked at Slot number " + SLOT_2_ID;
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
        this.parkingLot.setCarIDToSlotNumber(CAR_1_ID, SLOT_1_ID);
        String methodOutput = tapSystemOut(() -> this.parkingLot.unParkCar(CAR_1_ID)).trim();
        assertEquals("Slot " + SLOT_1_ID + " is free", methodOutput);
        assertNull(this.parkingLot.getCarIDToSlotNumberMap(CAR_1_ID));
    }

    @Test
    public void TestUnParkACarThatIsNotInTheParkingLot() {
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.parkingLot.unParkCar(CAR_NOT_PRESENT_ID)
        );
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }
}
