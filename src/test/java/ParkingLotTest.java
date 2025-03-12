import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {
    private static final int MAX_CAPACITY = 5;
    private static final int SLOT_NUMBER_FOR_SLOT_0 = 0;
    private static final int SLOT_NUMBER_FOR_SLOT_1 = 1;
    private static final int car1Id = 1;
    private static final int carNotPresentId = -1;
    
    private ParkingLot parkingLot;

    @Mock
    private SlotRecord slotRecord;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot(MAX_CAPACITY, this.slotRecord);
    }

    @Test
    public void testFindCar() throws Exception {
        this.parkingLot.parkCar(car1Id);
        this.parkingLot.getCarById(car1Id).parkInSlot(SLOT_NUMBER_FOR_SLOT_0);
        String methodOutput = tapSystemOut(() -> this.parkingLot.findCar(car1Id)).trim();
        assertEquals(car1Id + " is parked at Slot number " + SLOT_NUMBER_FOR_SLOT_0, methodOutput);
    }

    @Test
    public void testCanNotFindCar() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.findCar(carNotPresentId));
        assertEquals("Car with id: " + carNotPresentId + " could not be found", exception.getMessage());
    }

    @Test
    public void testPrint() throws Exception {
        int car2Id = 2;
        this.parkingLot.parkCar(car1Id);
        this.parkingLot.parkCar(car2Id);
        this.parkingLot.getCarById(car1Id).parkInSlot(SLOT_NUMBER_FOR_SLOT_0);
        this.parkingLot.getCarById(car2Id).parkInSlot(SLOT_NUMBER_FOR_SLOT_1);
        String methodOutput = tapSystemOut(() -> this.parkingLot.print()).trim();
        assertEquals(
                car1Id + " is parked at Slot number " + SLOT_NUMBER_FOR_SLOT_0
                + "\n" + car2Id + " is parked at Slot number " + SLOT_NUMBER_FOR_SLOT_1,
                methodOutput
        );
    }

    @Test
    public void testPrintWithNoCarsInParkingLot() throws Exception {
        String methodOutput = tapSystemOut(() -> this.parkingLot.print()).trim();
        assertEquals("No cars in the Parking Lot", methodOutput);
    }

    @Test
    public void testUnParkCar() {
        this.parkingLot.parkCar(car1Id);
        this.parkingLot.getCarById(car1Id).parkInSlot(SLOT_NUMBER_FOR_SLOT_0);
        doNothing().when(this.slotRecord).removeCar(any(Car.class));
        this.parkingLot.unParkCar(car1Id);
        verify(this.slotRecord, times(1)).removeCar(any(Car.class));
    }

    @Test
    public void TestUnParkACarThatIsNotInTheParkingLot() {
        var exception = assertThrows(RuntimeException.class, () -> this.parkingLot.unParkCar(carNotPresentId));
        assertEquals("Can not un-park car with id: " + carNotPresentId, exception.getMessage());
    }
}
