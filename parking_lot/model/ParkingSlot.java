package parking_lot.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import parking_lot.model.enums.VehicleAttribute;

public class ParkingSlot {
    // Unique across all slots.
    private UUID id;

    private List<VehicleAttribute> supportedVehicleAttributes;

    private Floor floor;

    // Assumes there is single entry on any floor.
    private Integer distanceFromFloorEntry;

    public UUID getId() {
        return this.id;
    }

    public List<VehicleAttribute> getSupportedVehicleAttributes() {
        return this.supportedVehicleAttributes;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public Integer getDistanceFromFloorEntry() {
        return this.distanceFromFloorEntry;
    }

    private ParkingSlot(Builder builder) {
        this.id = builder.id;
        this.supportedVehicleAttributes = builder.supportedVehicleAttributes;
        this.floor = builder.floor;
        this.distanceFromFloorEntry = builder.distanceFromFloorEntry;
    }

    public static class Builder {
        private List<VehicleAttribute> supportedVehicleAttributes = new LinkedList<>();
        private Floor floor;
        private Integer distanceFromFloorEntry;
        private UUID id;

        public Builder setFloor(Floor floor) {
            this.floor = floor;
            return this;
        }

        public Builder setDistanceForFloorEntry(Integer distanceFromFloorEntry) {
            this.distanceFromFloorEntry = distanceFromFloorEntry;
            return this;
        }

        public Builder addSupportedVehicleAttributes(VehicleAttribute vehicleAttribute) {
            this.supportedVehicleAttributes.add(vehicleAttribute);
            return this;
        }

        public Builder addAllSupportedVehicleAttributes(List<VehicleAttribute> vehicleAttributes) {
            this.supportedVehicleAttributes.addAll(vehicleAttributes);
            return this;
        }

        public ParkingSlot build() {
            this.id = UUID.randomUUID();
            return new ParkingSlot(this);
        }
    }


}