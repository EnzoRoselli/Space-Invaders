package Controller;

import Model.SQL;
import View.BestPlayers;
import View.Menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to get the best player using tree map and Json.
 * User has Person as a superclass.
 * @author InvadersTeam
 * @since March 2019.
 */
public class BestPlayer {

    private SQL statement;
    private JSON jsonHandler;
    private BestPlayers bestPlayersForm;
    private VisibleFramesHandler visible;
    private ArrayList<OnlyBestPlayerUser> users;

    /**
     *
     */
    public BestPlayer() {
        visible = new VisibleFramesHandler();
        bestPlayersForm = new BestPlayers();
        users = new ArrayList<OnlyBestPlayerUser>();
    }

    /**
     * This class creates a Json object from a tree map of best players.
     * @return String with a tree map of best players in Json format.
     */
    public String treeMapToJSON() {
        statement = new SQL();
        jsonHandler = new JSON();
        TreeMap<Integer, String> players = statement.dataBaseToTreeMap();
        JSONArray jsonArray  = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            for (Map.Entry<Integer, String> entry : players.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();

                jsonArray.put(jsonHandler.getFormatoJson(key, value));
            }

            jsonObject.put("best players", jsonArray);
            jsonHandler.createJSON(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonObject.toString();
    }

    /**
     * Gets the best player information.
     * @throws JSONException
     */
    public void JSONList() throws JSONException {
        JSONObject answer = new JSONObject(this.treeMapToJSON());
        JSONArray bestPlayersList = answer.getJSONArray("best players");

        for (int i = bestPlayersList.length()-1; i >=0 ; i--) {
            answer = bestPlayersList.getJSONObject(i);
            users.add(new OnlyBestPlayerUser(answer.getInt("score"), answer.getString("nickName"))); // variable anonima
        }
        bestPlayersForm.listBestPlayers(users);
    }
}
