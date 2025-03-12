import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Mock private SlotRecord slotRecord;
    @Mock private Car car;
    @Mock private Car car2;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY, this.slotRecord);
        lenient().doNothing().when(this.car).printInformation();
    }

    @Test
    public void testParkCar() {
        doNothing().when(this.slotRecord).addCar(any(Car.class));
        this.parkingLot.parkCar(CAR_1_ID);
        verify(this.slotRecord, times(1)).addCar(any(Car.class));
        assertNotNull(this.parkingLot.getCarFromCarRecordById(CAR_1_ID));
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
    public void testPrint() {
        this.parkingLot.addCarToCarRecord(CAR_1_ID, this.car);
        this.parkingLot.addCarToCarRecord(CAR_2_ID, this.car2);
        this.parkingLot.print();
        verify(this.car, times(1)).printInformation();
        verify(this.car2, times(1)).printInformation();
    }

    @Test
    public void testPrintWithNoCarsInParkingLot() throws Exception {
        String methodOutput = tapSystemOut(() -> this.parkingLot.print()).trim();
        assertEquals("No cars in the Parking Lot", methodOutput);
    }

    @Test
    public void testUnParkCar() {
        doNothing().when(this.car).unPark();
        this.parkingLot.addCarToCarRecord(CAR_1_ID, this.car);
        this.parkingLot.unParkCar(CAR_1_ID);
        verify(this.car, times(1)).unPark();
    }

    @Test
    public void TestUnParkACarThatIsNotInTheParkingLot() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.unParkCar(CAR_NOT_PRESENT_ID));
        assertEquals("Can not un-park car with id: " + CAR_NOT_PRESENT_ID, exception.getMessage());
    }
}
