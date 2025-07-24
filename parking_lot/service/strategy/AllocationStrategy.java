package parking_lot.service.strategy;

import parking_lot.model.ParkingSlot;
import parking_lot.model.Vehicle;

public interface AllocationStrategy {
    public ParkingSlot allocateSlot(Vehicle vehicle);
}