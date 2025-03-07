import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotTest {
    private Slot slot1;
    private Slot slot2;
    private Car car1;
    private Car car2;

    @BeforeEach
    public void setUp() {
        this.slot1 = new Slot(1, 1);
        this.slot2 = new Slot(2, 2);
        this.slot1.setNextSlot(this.slot2);
        this.car1 = new Car(1);
        this.car2 = new Car(2);
    }

    @Test
    public void testParkCar() {
        this.slot1.parkCar(this.car1);
        assertEquals(this.car1, this.slot1.getCar());
    }

    @Test
    public void testSendsCarToNextSlot() {
        this.slot1.parkCar(this.car1);
        this.slot1.parkCar(this.car2);
        assertEquals(this.car2, this.slot2.getCar());
    }

    @Test
    public void testFinalSlotThrowsParkingLotIsFullException() {
        this.slot2.parkCar(this.car1);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot2.parkCar(this.car2));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testAssignSlotNumberToCar() {
        this.slot1.parkCar(this.car1);
        assertEquals(this.slot1.getNumber(), this.car1.getSlotNumber());
    }

    @Test
    public void testFindCarThatIsPresent() throws Exception {
        this.slot1.parkCar(this.car1);
        String methodOutput = tapSystemOut(() -> {
            this.slot1.getCar(this.car1.getId());
        });
        assertEquals("1 is parked at Slot number 1", methodOutput.trim());
    }

    @Test
    public void testFindCarThatIsNotPresent() throws Exception {
        this.slot1.parkCar(this.car2);
        this.slot2.parkCar(this.car1);
        String methodOutput = tapSystemOut(() -> {
            this.slot1.getCar(this.car1.getId());
        });
        assertEquals("1 is parked at Slot number 2", methodOutput.trim());
    }
}
