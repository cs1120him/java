package design_patterns.builder.recursive_generics;

public class Person {
    private final String name;

    protected Person(AbstractBuilder<?, ?> b) {
        this.name = b.name;
    }

    public static class Builder extends AbstractBuilder<Person, Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Person build() {
            return new Person(self());
        }
    }

    public static abstract class AbstractBuilder<T extends Person, B extends AbstractBuilder<T, B>> {
        public String name;

        public B setName(String name) {
            this.name = name;
            return self();
        }

        protected abstract B self();
        public abstract T build();
    }
}