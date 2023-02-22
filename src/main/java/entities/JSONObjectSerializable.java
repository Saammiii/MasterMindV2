package entities;

import org.json.JSONObject;

import java.io.Serializable;

public class JSONObjectSerializable implements Serializable {
    private transient JSONObject jsonObject;

    public JSONObjectSerializable(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
