import design_patterns.builder.User;

public class HelloWorld {
    public static void main (String args[]) {
        System.out.println("Java Hello World");

        // Testing Builder pattern
        User user = new User.Builder().setFirstName("himanshu").setAge(30).build();
        System.out.println(user.toString());
    }
}