package Controller;

/**
 * This class creates a new player.
 * @author InvadersTeam
 * @since March 2019
 
 */
public class User extends Person {

    private String nickName, password, gmail;
    private final String rePassword;

    /**
     * This constructor receives and assigns all the user information.
     * @param name Player name.
     * @param lastName Player last name.
     * @param nickName Player nick name.
     * @param password Player password.
     * @param rePassword Password control.
     * @param gmail Player gmail account.
     */
    public User(String name, String lastName, String nickName, String password, String rePassword, String gmail) {
        super(name, lastName);
        this.nickName = nickName;
        this.password = password;
        this.rePassword = rePassword;
        this.gmail = gmail;
    }

    /**
     *
     * @return Player gmail account.
     */
    public String getGmail() {
        return gmail;
    }

    /**
     *
     * @return Password control.
     */
    public String getRePassword() {
        return rePassword;
    }

    /**
     *
     * @return Player nick name.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Receives and assigns the player nickname.
     * @param nickName Player nick name.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     *
     * @return Player password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Receives and assigns the player password.
     * @param password Player password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return Player gmail account.
     */
    public String getMail() {
        return gmail;
    }

    /**
     * Receives and assigns the player gmail account.
     * @param gmail Player gmail account.
     */
    public void setMail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String showWelcome() {
        return "Welcome " + this.getName() + ", save us from the invasion with " + this.getNickName();
    }
}
