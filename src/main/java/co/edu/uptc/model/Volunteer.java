package co.edu.uptc.model;

public class Volunteer extends User {
    private int age;
    private String email;

    public Volunteer(String username, String password, int age, String email) {
        super(username, password, "volunteer");
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Volunteer{name='" + getUsername() + "', email='" + email + "', age=" + age + "}";
    }
}