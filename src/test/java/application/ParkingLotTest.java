package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {
    private static final int MAX_CAPACITY = 5;
    private static final int CAR_1_ID = 1;
    private static final int SLOT_1_ID = 0;
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
//
//    @Test
//    public void testFindCar() {
//        this.parkingLot.findCar(CAR_1_ID);
//    }

    @Test
    public void testCanNotFindCar() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.findCar(CAR_NOT_PRESENT_ID));
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

//    @Test
//    public void testListCars() {
//        this.parkingLot.listCars(List.of(CAR_1_ID, CAR_2_ID));
//    }

    @Test
    public void testListCarsWithWrongCarId() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.listCars(List.of(CAR_NOT_PRESENT_ID)));
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

//    @Test
//    public void testUnParkCar() {
//        this.parkingLot.unParkCar(CAR_1_ID);
//    }

    @Test
    public void TestUnParkACarThatIsNotInTheParkingLot() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.unParkCar(CAR_NOT_PRESENT_ID));
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void testParkInSlot() throws Exception {

    }

//    @Test
//    public void testPrintInformation() throws Exception {
//        this.car.parkInSlot(this.slotId);
//        String methodOutput = tapSystemOut(() -> this.car.printInformation()).trim();
//        assertEquals(this.carId + " is parked at Slot number " + this.slotId, methodOutput);
//    }
}
