package co.edu.uptc.persistence;

import co.edu.uptc.model.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JSONPersistence {
    private static final String USERS_FILE = "users.json";
    private static final String ACTIVITIES_FILE = "activities.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(User.class, new UserAdapter())
            .registerTypeAdapter(Activity.class, new ActivityAdapter())
            .setPrettyPrinting()
            .create();

    public static List<User> loadUsers() {
        try (Reader reader = new FileReader(USERS_FILE)) {
            Type listType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveUsers(List<User> users) {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Activity> loadActivities() {
        try (Reader reader = new FileReader(ACTIVITIES_FILE)) {
            Type listType = new TypeToken<List<Activity>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveActivities(List<Activity> activities) {
        try (Writer writer = new FileWriter(ACTIVITIES_FILE)) {
            gson.toJson(activities, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
