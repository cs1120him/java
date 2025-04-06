package design_patterns.builder.faceted_builder;

public class Adult {
    // Address
    private final String street, city, state, country, pincode;

    // Work
    private final String company, workCity, title, startedIn;

    private Adult(Builder b) {
        this.street = b.street;
        this.city = b.city;
        this.state = b.state;
        this.country = b.country;
        this.pincode = b.pincode;
        this.company = b.company;
        this.workCity = b.workCity;
        this.title = b.title;
        this.startedIn = b.startedIn;
    }

    public static class Builder {
        private String street, city, state, country, pincode, company, workCity, title, startedIn;

        // These must be public as the client code that may call these methods might be outside the package.
        public AddressBuilder livesIn() {
            return new AddressBuilder(this);
        }

        public WorkBuilder worksAt() {
            return new WorkBuilder(this);
        }

        public Adult build() {
            return new Adult(this);
        }
    }

    public static class AddressBuilder extends Builder {
        private Builder b = new Builder();

        protected AddressBuilder(Builder b) {
            this.b = b;
        }

        public AddressBuilder setStreet(String street) {
            this.b.street = street;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.b.city = city;
            return this;
        }

        public AddressBuilder setState(String state) {
            this.b.state = state;
            return this;
        }

        public AddressBuilder setCountry(String country) {
            this.b.country = country;
            return this;
        }

        public AddressBuilder setPincode(String pincode) {
            this.b.pincode = pincode;
            return this;
        }
    }

    public static class WorkBuilder extends Builder {
        private Builder b = new Builder();

        protected WorkBuilder(Builder b) {
            this.b = b;
        }

        public WorkBuilder setCompany(String company) {
            this.b.company = company;
            return this;
        }

        public WorkBuilder setWorkCity(String workCity) {
            this.b.workCity = workCity;
            return this;
        }

        public WorkBuilder setTitle(String title) {
            this.b.title = title;
            return this;
        }

        public WorkBuilder setStartedIn(String startedIn) {
            this.b.startedIn = startedIn;
            return this;
        }
    }
}