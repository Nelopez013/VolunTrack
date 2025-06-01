package co.edu.uptc.model;

import java.util.Date;

public class InPersonActivity extends Activity {
    private String location;

    public InPersonActivity(String name, String description, Date date, int maxCapacity, String location) {
        super(name, description, date, maxCapacity);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String getType() {
        return "in-person";
    }

    @Override
    public String toString() {
        return super.toString() + ", Location: " + location;
    }
}