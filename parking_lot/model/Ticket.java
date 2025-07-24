package parking_lot.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    // Unique across all tickets.
    private UUID id;

    private Vehicle vehicle;

    private ParkingSlot slot;

    private final LocalDateTime entryDateTime;

    private LocalDateTime exitDateTime;

    public UUID getId() {
        return this.id;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlot getSlot() {
        return this.slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public LocalDateTime getEntryDateTime() {
		return this.entryDateTime;
    }

    public LocalDateTime getExitDateTime() {
        return this.exitDateTime;
    }

    public void setExitDateTime(LocalDateTime exitDateTime) {
        this.exitDateTime = exitDateTime;
    }

    // No default constructor as there is a defined constructor.
    public Ticket(Vehicle vehicle, ParkingSlot slot) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryDateTime = LocalDateTime.now();
    }
}