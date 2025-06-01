package co.edu.uptc.model;

import java.util.Date;

public class VirtualActivity extends Activity {
    private String platform;

    public VirtualActivity(String name, String description, Date date, int maxCapacity, String platform) {
        super(name, description, date, maxCapacity);
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public String getType() {
        return "virtual";
    }

    @Override
    public String toString() {
        return super.toString() + ", Platform: " + platform;
    }
}
