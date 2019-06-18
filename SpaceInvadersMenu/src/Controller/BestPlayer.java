package Controller;

import Model.SQL;
import View.BestPlayers;
import View.Menu;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BestPlayer {

    private SQL statement;
    private JSON jsonHandler;
    private BestPlayers bestPlayersForm;
    private VisibleFramesHandler visible;
    
    public BestPlayer() {
        visible = new VisibleFramesHandler();
        bestPlayersForm = new BestPlayers();
    }

    public String treeMapToJSON() {
        statement = new SQL();
        jsonHandler = new JSON();
        TreeMap<Integer, String> players = statement.dataBaseToTreeMap();
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            for (Map.Entry<Integer, String> entry : players.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();

                array.put(jsonHandler.getFormatoJson(key,value));
            }

            jsonObject.put("best players", array);
            jsonHandler.createJSON(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jsonObject.toString();
    }
    
    public void listJSON() throws JSONException{
    JSONObject answer = new JSONObject(this.treeMapToJSON()); 
    JSONArray bestPlayersList = answer.getJSONArray("best players");
    int x=20,y=100,width=60,height=-1;
    String list="";
    
        for (int i = bestPlayersList.length()-1; i >= 0; i--) {
            answer = bestPlayersList.getJSONObject(i);
            list="Nick Name: "+answer.getString("nickName")+"\nScore: "+answer.getInt("score");
            bestPlayersForm.listBestPlayers(list,x,y+=20,width,height);
        }
        
        
        visible.visibleFrame(bestPlayersForm);
    }
    

}
