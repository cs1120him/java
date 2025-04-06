package design_patterns.builder.recursive_generics;

public class Employee extends Person {
    private final String id;

    private Employee(Builder b) {
        super(b);
        this.id = b.id;
    }

    public static class Builder extends Person.AbstractBuilder<Employee, Builder> {
        public String id;

        public Builder setId(String id) {
            this.id = id;
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(self());
        }

    }
}