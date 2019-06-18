package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class creates and writes a JSON file.
 * @author InvadersTeam
 * @since March 2019
 */
public class JSON {

    /**
     *
     */
    public JSON() {
    }

    /**
     * Adds an object to Json file using "nickname" as the object name.
     * @param 
     * @return Json object.
     * @throws JSONException
     */
    public JSONObject getFormatoJson(Integer score, String nickName) throws JSONException {
        JSONObject objeto = new JSONObject();
        objeto.put("score",score);
        objeto.put("nickName",nickName);
        
        return objeto;
    }
    
    /**
     * Creates a JSON file and writes it with an object.
     * @param object JSON object tha is going to be writed on JSON file.
     * @throws IOException
     */
    public void createJSON(JSONObject object) throws IOException{
        try (FileWriter file = new FileWriter("players.json")) {
            file.write(object.toString());
            file.flush();
        }
    }

}
