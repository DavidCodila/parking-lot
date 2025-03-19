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
    private static final int CAR_2_ID = 2;
    private static final int CAR_NOT_PRESENT_ID = -1;
    
    private ParkingLot parkingLot;
    @Mock Slot slot;
    @Mock private SlotHandler slotRecord;
    @Mock private Car car;
    @Mock private Car car2;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY, this.slotRecord);
        lenient().doNothing().when(this.car).printInformation();
    }

    @Test
    public void testParkCar() {
        when(this.slotRecord.getNextVacantSlot()).thenReturn(this.slot);
        this.parkingLot.parkCar(CAR_1_ID);
        verify(this.slot, times(1)).parkCar(any(Car.class));
        assertNotNull(this.parkingLot.getCarFromCarRecordById(CAR_1_ID));
    }

    @Test
    public void testParkTooManyCars() {
        when(this.slotRecord.getNextVacantSlot()).thenReturn(this.slot);
        for (int i = 0; i < MAX_CAPACITY; i++) {
            this.parkingLot.parkCar(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(MAX_CAPACITY));
        assertEquals("Can not park car, parking lot is full", exception.getMessage());
    }

    @Test
    public void testFindCar() {
        this.parkingLot.addCarToCarRecord(CAR_1_ID, this.car);
        this.parkingLot.findCar(CAR_1_ID);
        verify(this.car, times(1)).printInformation();
    }

    @Test
    public void testCanNotFindCar() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.findCar(CAR_NOT_PRESENT_ID));
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void testListCars() {
        this.parkingLot.addCarToCarRecord(CAR_1_ID, this.car);
        this.parkingLot.addCarToCarRecord(CAR_2_ID, this.car2);
        this.parkingLot.listCars(List.of(CAR_1_ID, CAR_2_ID));
        verify(this.car, times(1)).printInformation();
        verify(this.car2, times(1)).printInformation();
    }

    @Test
    public void testListCarsWithNoCarsInParkingLot() throws Exception {
        String methodOutput = tapSystemOut(() -> this.parkingLot.listCars(List.of(CAR_1_ID))).trim();
        assertEquals("No cars in the Parking Lot", methodOutput);
    }

    @Test
    public void testListCarsWithWrongCarId() {
        this.parkingLot.addCarToCarRecord(CAR_1_ID, this.car);
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.listCars(List.of(CAR_NOT_PRESENT_ID)));
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }

    @Test
    public void testUnParkCar() {
        doNothing().when(this.car).unPark();
        this.parkingLot.addCarToCarRecord(CAR_1_ID, this.car);
        this.parkingLot.unParkCar(CAR_1_ID);
        verify(this.car, times(1)).unPark();
        assertNull(this.parkingLot.getCarFromCarRecordById(CAR_1_ID));
    }

    @Test
    public void TestUnParkACarThatIsNotInTheParkingLot() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.unParkCar(CAR_NOT_PRESENT_ID));
        assertEquals("Car with id: " + CAR_NOT_PRESENT_ID + " could not be found", exception.getMessage());
    }
}
