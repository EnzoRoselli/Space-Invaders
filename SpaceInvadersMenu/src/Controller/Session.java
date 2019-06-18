package Controller;

/**
 * This class createsa new game session.
 * @author InvadersTeam
 * @since March 2019.
 */
public class Session {
    private String user_gmail;
    private String password;
    
    /**
     *
     * @param user Player gmail.
     * @param password Player password.
     */
    public Session(String user, String password) {
        this.user_gmail=user;
        this.password=password;
    }

    /**
     *
     * @return  Player gmail.
     */
    public String getUser_gmail() {
        return user_gmail;
    }

    /**
     *
     * @return Player password.
     */
    public String getPassword() {
        return password;
    }
}
