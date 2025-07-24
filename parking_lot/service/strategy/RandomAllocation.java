package parking_lot.service.strategy;

import parking_lot.data.manager.ParkingSlotsManager;
import parking_lot.model.ParkingSlot;
import parking_lot.model.Vehicle;

public class RandomAllocation implements AllocationStrategy {
    private ParkingSlotsManager parkingSlotsManager;

    public RandomAllocation(ParkingSlotsManager parkingSlotsManager) {
        this.parkingSlotsManager = parkingSlotsManager;
    }

    public ParkingSlot allocateSlot(Vehicle vehicle) {
        ParkingSlot slot = this.parkingSlotsManager.findRandomSlot(vehicle);
        this.parkingSlotsManager.occupyParkingSlot(vehicle, slot);
        return slot;
    }
}