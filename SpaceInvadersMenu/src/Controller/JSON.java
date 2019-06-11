package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

    public JSON() {
    }

    public JSONObject getFormatoJson(String player) throws JSONException {
        JSONObject objeto = new JSONObject();
        objeto.put("nickName",player);
        return objeto;
    }
    
    public void createJSON(JSONObject object) throws IOException{
        try (FileWriter file = new FileWriter("players.json")) {
            file.write(object.toString());
            file.flush();
        }
    }

}
