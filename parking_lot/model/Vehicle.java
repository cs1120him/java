package parking_lot.model;

import java.util.List;

import parking_lot.model.enums.VehicleAttribute;

public class Vehicle {
    // Unique across all vehicles
    private String number;

    private List<VehicleAttribute> attributes;

    public String getNumber() {
        return this.number;
    }

    public List<VehicleAttribute> getAttributes() {
        return this.attributes;
    }

    private Vehicle(Builder builder) {
        this.number = builder.number;
        this.attributes = builder.attributes;
    }

    public static class Builder {
        private String number;
        private List<VehicleAttribute> attributes;

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder addAllAttributes(List<VehicleAttribute> attributes) {
            this.attributes.addAll(attributes);
            return this;
        }

        public Builder addAttribute(VehicleAttribute attribute) {
            this.attributes.add(attribute);
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }

}