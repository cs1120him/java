package parking_lot.data.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import parking_lot.model.Floor;
import parking_lot.model.ParkingSlot;
import parking_lot.model.Vehicle;

public class ParkingSlotsRepo {
    private static final ParkingSlotsRepo INSTANCE = new ParkingSlotsRepo();

    private List<Floor> floors = new ArrayList<>();

    private Set<ParkingSlot> availableSlots = ConcurrentHashMap.newKeySet();
    // Index to find nearest slots quickly.
    private Map<Integer, Set<ParkingSlot>> floorLevelToAvailableSlotsMap = new HashMap<>();

    private Set<ParkingSlot> occupiedSlots = new HashSet<>();
    // Index to deallocate a slot quickly.
    private Map<String, ParkingSlot> parkedVehicleToOccupiedSlotMap = new HashMap<>();
    private Map<UUID, Vehicle> occupiedSlotToParkedVehicleMap = new HashMap<>();

    public Map<Integer, Set<ParkingSlot>> getFloorLevelToAvailableSlotsMap() {
        return this.floorLevelToAvailableSlotsMap;
    }

    public List<Floor> getFloors() {
        return this.floors;
    }

    public Set<ParkingSlot> getAvailableSlots() {
        return this.availableSlots;
    }

    public Map<String, ParkingSlot> getParkedVehicleToOccupiedSlotMap() {
        return this.parkedVehicleToOccupiedSlotMap;
    }

    public Set<ParkingSlot> getOccupiedSlots() {
        return this.occupiedSlots;
    }

    private ParkingSlotsRepo() {}

    public static ParkingSlotsRepo getInstance() {
        return INSTANCE;
    }

    public void addFloor(Floor floor) {
        this.floors.add(floor);
        this.floorLevelToAvailableSlotsMap.put(floor.getLevel(),
            new TreeSet<>(new Comparator<ParkingSlot>() {
                @Override
                public int compare(ParkingSlot a, ParkingSlot b) {
                    if (a.getDistanceFromFloorEntry() < b.getDistanceFromFloorEntry()) return -1;
                    if (a.getDistanceFromFloorEntry() > b.getDistanceFromFloorEntry()) return 1;
                    return a.getId().toString().compareTo(b.getId().toString());
                }
            }));
    }

    public void addParkingSlot(ParkingSlot slot) {
        this.availableSlots.add(slot);
        this.floorLevelToAvailableSlotsMap.get(slot.getFloor().getLevel()).add(slot);
    }

    public void addAvailableSlot(ParkingSlot slot) {
        this.availableSlots.add(slot);
        this.floorLevelToAvailableSlotsMap.get(slot.getFloor().getLevel()).add(slot);
    }

    public void removeAvailableSlot(ParkingSlot slot) {
        if (!this.availableSlots.remove(slot)) ;
        this.floorLevelToAvailableSlotsMap.get(slot.getFloor().getLevel()).remove(slot);
    }

    public void addOccupiedSlot(Vehicle vehicle, ParkingSlot slot) {
        this.occupiedSlots.add(slot);
        this.parkedVehicleToOccupiedSlotMap.put(vehicle.getNumber(), slot);
        this.occupiedSlotToParkedVehicleMap.put(slot.getId(), vehicle);
    }

    public void removeOccupiedSlot(Vehicle vehicle) {
        ParkingSlot slot = this.parkedVehicleToOccupiedSlotMap.get(vehicle.getNumber());
        this.occupiedSlots.remove(slot);
        this.parkedVehicleToOccupiedSlotMap.remove(vehicle.getNumber());
        this.occupiedSlotToParkedVehicleMap.remove(slot.getId());
    }

    public void removeOccupiedSlot(ParkingSlot slot) {
        Vehicle vehicle = this.occupiedSlotToParkedVehicleMap.get(slot.getId());
        this.occupiedSlots.remove(slot);
        this.parkedVehicleToOccupiedSlotMap.remove(vehicle.getNumber());
        this.occupiedSlotToParkedVehicleMap.remove(slot.getId());
    }
}