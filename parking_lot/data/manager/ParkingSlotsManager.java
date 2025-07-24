package parking_lot.data.manager;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import parking_lot.data.repository.ParkingSlotsRepo;
import parking_lot.model.Floor;
import parking_lot.model.ParkingSlot;
import parking_lot.model.Vehicle;

public class ParkingSlotsManager {
    private ParkingSlotsRepo parkingSlotsRepo;

    public ParkingSlotsManager(ParkingSlotsRepo parkingSlotsRepo) {
        this.parkingSlotsRepo = parkingSlotsRepo;
    }

    public void addParkingSlot(ParkingSlot slot) {
        Floor floor = slot.getFloor();
        if (!this.parkingSlotsRepo.getFloorLevelToAvailableSlotsMap().containsKey(floor.getLevel()))
            this.parkingSlotsRepo.addFloor(floor);
        if (!this.parkingSlotsRepo.getFloorLevelToAvailableSlotsMap().get(floor.getLevel()).contains(slot))
            this.parkingSlotsRepo.addParkingSlot(slot);
    }

    public ParkingSlot findRandomSlot(Vehicle vehicle) {
        List<ParkingSlot> suitableSlots = this.parkingSlotsRepo.getAvailableSlots().stream().filter(
            slot -> isSlotSuitableForVehicle(slot, vehicle))
            .collect(Collectors.toList());

        if (suitableSlots.size() == 0) return null;
        int randomIndex = ThreadLocalRandom.current().nextInt(0, suitableSlots.size());
        return suitableSlots.get(randomIndex);
    }

    public ParkingSlot findAnySlot(Vehicle vehicle) {
        Iterator<ParkingSlot> itr = this.parkingSlotsRepo.getAvailableSlots().iterator();
        while(itr.hasNext()) {
            ParkingSlot slot = itr.next();
            if (isSlotSuitableForVehicle(slot, vehicle)) return slot;
        }
        return null;
    }

    public ParkingSlot findNearestSlot(Vehicle vehicle) {
        for (int floorLevel = 0; floorLevel < this.parkingSlotsRepo.getFloors().size(); floorLevel++) {
            Iterator<ParkingSlot> itr = this.parkingSlotsRepo.getFloorLevelToAvailableSlotsMap().get(floorLevel).iterator();
            while(itr.hasNext()) {
                ParkingSlot slot = itr.next();
                if (isSlotSuitableForVehicle(slot, vehicle)) return slot;
            }
        }

        return null;
    }

    public void occupyParkingSlot(Vehicle vehicle, ParkingSlot slot) {
        this.parkingSlotsRepo.removeAvailableSlot(slot);
        this.parkingSlotsRepo.addOccupiedSlot(vehicle, slot);
    }

    public void exitParkingSlot(ParkingSlot slot) {
        this.parkingSlotsRepo.removeOccupiedSlot(slot);
        this.parkingSlotsRepo.addAvailableSlot(slot);
    }

    private boolean isSlotSuitableForVehicle(ParkingSlot slot, Vehicle vehicle) {
        return slot.getSupportedVehicleAttributes().containsAll(vehicle.getAttributes());
    }
}