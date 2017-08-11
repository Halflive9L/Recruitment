package be.xplore.recruitment;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.List;

public class JSONObjectBuilder {
    private JSONObject obj;

    private JSONObjectBuilder() {
        obj = new JSONObject();
    }

    public static JSONObjectBuilder aJsonObject() {
        return new JSONObjectBuilder();
    }

    public JSONObjectBuilder with(String key, Object value) {
        obj.put(key, value);
        return this;
    }

    public <T> JSONObjectBuilder withList(String key, List<T> list) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        obj.put(key, jsonArray);
        return this;
    }

    public JSONObject build() {
        return obj;
    }
}
