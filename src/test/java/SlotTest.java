import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SlotTest {
    private Slot slot;
    private Car car;

    @BeforeEach
    public void setUp() {
        this.slot = new Slot(1, 1);
        this.slot.setNextSlot(new Slot(2, 2));
        this.car = new Car(0);
    }

    @Test
    public void testParkParkCar() {
        this.slot.parkCar(this.car);
        assertEquals(this.car, this.slot.getCar());
    }

    @Test
    public void testParkUnParkCar() {
        this.slot.parkCar(this.car);
        this.slot.unParkCar();
        assertNull(this.slot.getCar());
    }

    @Test
    public void testSendsCarToNextSlot() {
        this.slot.parkCar(this.car);
        Car car2 = new Car(1);
        this.slot.parkCar(car2);
        assertEquals(car2, this.slot.getNextSlot().getCar());
    }

    @Test
    public void testSlotToSlotDistanceCompareToCloser() {
        assertEquals(1, this.slot.compareTo(new Slot(2, 2)));
    }

    @Test
    public void testSlotToSlotDistanceCompareToEqual() {
        assertEquals(0, this.slot.compareTo(new Slot(2, 1)));
    }

    @Test
    public void testSlotToSlotDistanceCompareToFurther() {
        assertEquals(-1, this.slot.compareTo(new Slot(2, 0)));
    }
}
