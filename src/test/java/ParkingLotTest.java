import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    private int maxCapacity;
    private ParkingLot parkingLot;
    private List<Integer> carIds;

    @BeforeEach
    public void setUp() {
        this.parkingLot = new ParkingLot();
        this.maxCapacity = this.parkingLot.MAX_CAPACITY;
        this.carIds = List.of(1, 2, 3);
    }

    @Test
    public void testParkCar() {
        this.parkingLot.parkCar(this.carIds.getFirst());
        assertEquals(this.carIds.getFirst(), this.parkingLot.getSlotAtIndex(0).getCar().getId());
    }

    @Test
    public void testParkTwoCarIds() {
        this.parkingLot.parkCar(this.carIds.getFirst());
        this.parkingLot.parkCar(this.carIds.get(1));
        assertEquals(this.carIds.get(1), this.parkingLot.getSlotAtIndex(1).getCar().getId());
    }

    @Test
    public void testParkTooManyCarIds() {
        for (int i = 0; i < this.maxCapacity; i++) {
            this.parkingLot.parkCar(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.parkingLot.parkCar(this.maxCapacity));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        this.parkingLot.getSlotAtIndex(0).parkCar(new Car(1));
        this.parkingLot.getSlotAtIndex(1).parkCar(new Car(2));
        this.parkingLot.parkCar(this.carIds.get(2));
        assertEquals(this.carIds.get(2), this.parkingLot.getSlotAtIndex(2).getCar().getId());
    }
}
