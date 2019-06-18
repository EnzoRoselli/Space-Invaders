package Controller;

/**
 *
 * @author InvadersTeam
 */
public class Session {
    private String user_gmail;
    private String password;
    
    /**
     *
     * @param user
     * @param password
     */
    public Session(String user, String password) {
        this.user_gmail=user;
        this.password=password;
    }

    /**
     *
     * @return
     */
    public String getUser_gmail() {
        return user_gmail;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
}
