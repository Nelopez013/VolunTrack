package co.edu.uptc.persistence;

import com.google.gson.*;
import co.edu.uptc.model.*;

import java.lang.reflect.Type;

public class UserAdapter implements JsonSerializer<User>, JsonDeserializer<User> {
    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", user.getName());
        jsonObject.addProperty("lastname", user.getLastname());
        jsonObject.addProperty("username", user.getUsername());
        jsonObject.addProperty("age", user.getAge());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("password", user.getPassword());
        jsonObject.addProperty("role", user.getRole());
        return jsonObject;
    }

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        String lastname = jsonObject.get("lastname").getAsString();
        String username = jsonObject.get("username").getAsString();
        int age = jsonObject.get("age").getAsInt();
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();
        String role = jsonObject.get("role").getAsString();

        if (role.equalsIgnoreCase("volunteer")) {
            return new Volunteer(name, lastname, username, age, email, password,role);
        } else {
            return new User(name, lastname, username, age, email, password, role);
        }
    }
}