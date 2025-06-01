package co.edu.uptc.persistence;

import com.google.gson.*;
import co.edu.uptc.model.*;

import java.lang.reflect.Type;

public class ActivityAdapter implements JsonSerializer<Activity>, JsonDeserializer<Activity> {
    @Override
    public JsonElement serialize(Activity activity, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(activity).getAsJsonObject();
        jsonObject.addProperty("type", activity.getType());
        return jsonObject;
    }

    @Override
    public Activity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String activityType = "in-person"; // Valor por defecto

        if (jsonObject.has("type") && !jsonObject.get("type").isJsonNull()) {
            activityType = jsonObject.get("type").getAsString().toLowerCase();
        }

        return switch (activityType) {
            case "virtual" -> context.deserialize(json, VirtualActivity.class);
            case "in-person" -> context.deserialize(json, InPersonActivity.class);
            default -> throw new JsonParseException("Unknown activity type: " + activityType);
        };
    }
}
