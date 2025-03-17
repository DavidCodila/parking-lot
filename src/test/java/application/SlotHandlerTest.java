package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SlotHandlerTest {
    private static final int MAX_CAPACITY = 5;
    private static final int ZER0TH_INDEX = 0;
    private static final int FIRST_INDEX = 1;

    private SlotHandler slotHandler;
    @Mock private BasicRecordGenerator basicSlotRecordGenerator;
    @Mock private Car car1;
    @Mock private Car car2;


    @BeforeEach
    public void setUp() {
        this.basicSlotRecordGenerator = mock(BasicRecordGenerator.class);
        List<SlotRecord> slotList = new ArrayList<>(MAX_CAPACITY);
        for (int i = 0; i < MAX_CAPACITY; i++) {
            slotList.add(new SlotRecord(new Slot(i), i));
        }
        when(this.basicSlotRecordGenerator.generate()).thenReturn(slotList);
        this.slotHandler = new SlotHandler(this.basicSlotRecordGenerator);
        this.car1 = mock(Car.class);
        this.car2 = mock(Car.class);
    }

    @Test
    public void testParkCar() {
        this.slotHandler.parkCar(this.car1);
        assertEquals(this.car1, this.slotHandler.getCarAtIndex(ZER0TH_INDEX));
    }

    @Test
    public void testParkTwoCars() {
        this.slotHandler.parkCar(this.car1);
        this.slotHandler.parkCar(this.car2);
        assertEquals(this.car2, this.slotHandler.getCarAtIndex(FIRST_INDEX));
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = ZER0TH_INDEX; i < MAX_CAPACITY; i++) {
            this.slotHandler.parkCar(new Car(i));
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.slotHandler.parkCar(new Car(MAX_CAPACITY)));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        this.slotHandler.parkCar(this.car1);
        final int SECOND_INDEX = 2;
        Car car3 = mock(Car.class);
        this.slotHandler.setCarAtIndex(FIRST_INDEX, this.car2);
        this.slotHandler.parkCar(car3);
        assertEquals(car3, this.slotHandler.getCarAtIndex(SECOND_INDEX));
    }

    @Test
    public void testOnUnParkCar() {
        this.slotHandler.parkCar(this.car1);
        Slot slot = this.slotHandler.getSlotAtIndex(ZER0TH_INDEX);
        this.slotHandler.onUnParkCar(slot);
        assertNull(this.slotHandler.getSlotAtIndex(ZER0TH_INDEX));
    }

    @Test
    public void testExceptionThrownOnUnParkCar() {
        Slot slot = mock(Slot.class);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slotHandler.onUnParkCar(slot));
        assertEquals("Error with onUnParkCar method call", exception.getMessage());
    }
}
