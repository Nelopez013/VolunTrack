package co.edu.uptc.persistence;

import com.google.gson.*;
import co.edu.uptc.model.*;

import java.lang.reflect.Type;

public class UserAdapter implements JsonSerializer<User>, JsonDeserializer<User> {
    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", user.getUsername());
        jsonObject.addProperty("password", user.getPassword());
        jsonObject.addProperty("role", user.getRole());

        if (user instanceof Volunteer volunteer) {
            jsonObject.addProperty("age", volunteer.getAge());
            jsonObject.addProperty("email", volunteer.getEmail());
        }

        return jsonObject;
    }

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String role = jsonObject.get("role").getAsString();

        if (role.equalsIgnoreCase("volunteer")) {
            int age = jsonObject.get("age").getAsInt();
            String email = jsonObject.get("email").getAsString();
            return new Volunteer(username, password, age, email);
        } else {
            return new User(username, password, role);
        }
    }
}