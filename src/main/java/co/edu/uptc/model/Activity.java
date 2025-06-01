package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Activity {
    private String name;
    private String description;
    private Date date;
    private int maxCapacity;
    private List<User> volunteers;

    public Activity(String name, String description, Date date, int maxCapacity) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.maxCapacity = maxCapacity;
        this.volunteers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<User> getVolunteers() {
        return volunteers;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Date: " + date +
                ", Max Capacity: " + maxCapacity + ", Enrolled: " + volunteers.size() +
                ", Type: " + getType();
    }
}
