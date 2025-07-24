package parking_lot.model;

import java.util.UUID;

public class Floor {
    private final UUID id;

    private final Integer level;

    public UUID getId() {
        return this.id;
    }

    public Integer getLevel() {
        return this.level;
    }

    private Floor(Builder builder) {
        this.id = builder.id;
        this.level = builder.level;
    }

    public static class Builder {
        private UUID id;
        private Integer level;

        public Builder setLevel(Integer level) {
            this.level = level;
            return this;
        }

        public Floor build() {
            this.id = UUID.randomUUID();
            return new Floor(this);
        }

    }
}