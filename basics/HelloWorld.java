import design_patterns.builder.User;
import design_patterns.builder.faceted_builder.Adult;
import design_patterns.builder.recursive_generics.Employee;
import design_patterns.builder.recursive_generics.Person;

public class HelloWorld {
    public static void main (String args[]) {
        System.out.println("Java Hello World");

        // Testing Builder pattern
        User user = new User.Builder().setFirstName("himanshu").setAge(30).build();
        System.out.println(user.toString());

        // Testing Builder with inheritence pattern
        Person p = new Person.Builder().setName("himanshu").build();
        Employee e = new Employee.Builder().setName("pari").setId("123").build();

        // Testing Facade Builder pattern :- More than one builder for a class
        Adult a = new Adult.Builder()
            .livesIn().setCity("Bareilly").setPincode("243003")
            .worksAt().setCompany("Google").build();
    }
}