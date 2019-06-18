package MVC.Interface;


/**
 * This interface makes the password encryption.
 * @author InvadersTeam
 * @since March 2019
 */
public interface Iencryption {

    /**
     *
     * @param pass String password.
     * @return
     */
    String encryptedPassword(String pass);
}
