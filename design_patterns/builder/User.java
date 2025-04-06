package design_patterns.builder;

import java.lang.module.ModuleDescriptor.Builder;

public class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String address;
    private final Integer age;
    private final String gender;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.firstName == null) {
            sb.append("User has not first name!");
            return sb.toString();
        }
        sb.append("User named: ");
        sb.append(this.firstName);
        sb.append(" ");

        if (this.age != null) {
            sb.append("has age: ");
            sb.append(this.age);
        } else {
            sb.append("has not provided age");
        }
        return sb.toString();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private Integer age;
        private String gender;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}