package Controller;

import MVC.Interface.ImessageForUser;
import MVC.Interface.Iencryption;
import Model.*;

/**
 *
 * @author InvadersTeam
 */
public class LogInHandler implements Iencryption,ImessageForUser {

    private SQL statement;

    /**
     *
     */
    public LogInHandler() {
        statement = new SQL();
    }

    /**
     *
     * @param error
     * @param sesion
     * @return
     */
    public String logInManager(String error, Session sesion) {
        if (error.equals(NO)) {
            error = this.textAreasCompleted(sesion);
        }
        if (error.equals(NO)) {
            error = this.minimumSize(sesion.getPassword());
        }
        if(error.equals(NO)){
            error = this.maximumSize(sesion.getPassword());
        }
        if (error.equals(NO)) {
            error = statement.logInDB(sesion.getUser_gmail(), encryptedPassword(sesion.getPassword()));
        }
        return error;
    }
    
    //Agregar forgot password

    /**
     *
     * @param password
     * @return
     */
    
    public String maximumSize(String password) {
        if (password.length() > 15) {
            return MAXIMUM_SIZE;
        }
        return NO;
    }

    /**
     *
     * @param password
     * @return
     */
    public String minimumSize(String password) { ///meterla en una interface se usa 2 veces
        if (password.length() < 6) {
            return MINIMUM_SIZE;
        }
        return NO;
    }

    /**
     *
     * @param sesion
     * @return
     */
    public String textAreasCompleted(Session sesion) {
        if (sesion.getUser_gmail().equals("") || sesion.getPassword().equals("")) {
            return TEXT_AREAS_COMPLETED;
        }
        return NO;
    }

    /**
     *
     * @param pass
     * @return
     */
    @Override
    public String encryptedPassword(String pass) {
        String encryptedPass = Security.md5(pass);
        return encryptedPass;
    }
}
