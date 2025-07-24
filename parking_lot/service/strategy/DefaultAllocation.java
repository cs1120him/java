package parking_lot.service.strategy;

import parking_lot.data.manager.ParkingSlotsManager;
import parking_lot.model.ParkingSlot;
import parking_lot.model.Vehicle;

public class DefaultAllocation {
    private ParkingSlotsManager parkingSlotsManager;

    public DefaultAllocation(ParkingSlotsManager parkingSlotsManager) {
        this.parkingSlotsManager = parkingSlotsManager;
    }

    public ParkingSlot allocateSlot(Vehicle vehicle) {
        ParkingSlot slot = this.parkingSlotsManager.findAnySlot(vehicle);
        this.parkingSlotsManager.occupyParkingSlot(vehicle, slot);
        return slot;
    }
}