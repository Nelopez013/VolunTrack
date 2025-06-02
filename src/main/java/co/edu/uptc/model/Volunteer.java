package co.edu.uptc.model;

public class Volunteer extends User {

    public Volunteer(String name, String lastname, String username, int age, String email, String password, String role) {
        super(name, lastname, username, age, email, password,"volunteer");
       
    }

    @Override
        public String toString() {
            return "Volunteer {" +
                    "name='" + getName() + '\'' +
                    ", lastname='" + getLastname() + '\'' +
                    ", username='" + getUsername() + '\'' +
                    ", age=" + getAge() +
                    ", email='" + getEmail() + '\'' +
                    ", password='" + getPassword() + '\'' +
                    ", role='" + getRole() + '\'' +
                    '}';
    }
    
}