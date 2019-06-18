package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author InvadersTeam
 */
public class JSON {

    /**
     *
     */
    public JSON() {
    }

    /**
     *
     * @param player
     * @return
     * @throws JSONException
     */
    public JSONObject getFormatoJson(Integer score, String nickName) throws JSONException {
        JSONObject objeto = new JSONObject();
        objeto.put("score",score);
        objeto.put("nickName",nickName);
        
        return objeto;
    }
    
    /**
     *
     * @param object
     * @throws IOException
     */
    public void createJSON(JSONObject object) throws IOException{
        try (FileWriter file = new FileWriter("players.json")) {
            file.write(object.toString());
            file.flush();
        }
    }

}
