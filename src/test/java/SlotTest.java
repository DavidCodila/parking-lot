import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlotTest {
    @Test
    public void testParkParkCar() {
        Slot slot = new Slot(1);
        slot.parkCar(new Car());
        assertTrue(slot.isTaken());
    }

    @Test
    public void testParkUnParkCar() {
        Slot slot = new Slot(1);
        slot.parkCar(new Car());
        assertTrue(slot.isTaken());
        slot.unParkCar();
        assertFalse(slot.isTaken());
    }
}
