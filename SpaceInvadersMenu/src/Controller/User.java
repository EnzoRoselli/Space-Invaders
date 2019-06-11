package Controller;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends Person {

    private String nickName, password, gmail;
    private final String rePassword;

    public User(String name, String lastName, String nickName, String password, String rePassword, String gmail) {
        super(name, lastName);
        this.nickName = nickName;
        this.password = password;
        this.rePassword = rePassword;
        this.gmail = gmail;
    }

    public String getGmail() {
        return gmail;
    }

    public String getRePassword() {
        return rePassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return gmail;
    }

    public void setMail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String showWelcome() {
        return "Welcome " + this.getName() + ", save us from the invasion with " + this.getNickName();
    }
}
