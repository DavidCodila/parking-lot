import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    private Car car1;
    private int car1Id;

    @BeforeEach
    public void setUp() {
        this.car1Id = 1;
        this.car1 = new Car(this.car1Id);
    }

    @Test
    public void testParkInSlot() throws Exception {
        String methodOutput = tapSystemOut(() -> {
            this.car1.parkInSlot(1);
        });
        assertEquals("SLOT 1 is allocated to 1", methodOutput.trim());
    }

    @Test
    public void testPrintCarIdSlotId() {
        this.car1.parkInSlot(1);
        assertEquals("1 is parked at Slot number 1", this.car1.printCarIdSlotId(this.car1Id));
    }

    @Test
    public void testPrintCarIdSlotIdIfNotRightId() {
        this.car1.parkInSlot(0);
        assertEquals("", this.car1.printCarIdSlotId(0));
    }
}
