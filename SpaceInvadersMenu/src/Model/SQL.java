package Model;

import MVC.Interface.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This class
 * @author InvadersTeam
 * @since March 2019
 * @see iquerys.
 * @see imessageForUser. 
 * 
 */
public class SQL implements Iquerys, ImessageForUser {

    private Statement statement;
    private PreparedStatement pStatement;
    private Connection connection;
    private ResultSet resultset;

    /**
     *
     */
    public SQL() {
    }

    /**
     * Makes the connection with the data base.
     * @return Connection status.
     */
    public Connection connectionDB() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(PORT, USER, PASSWORD);
            System.out.println("Successful connection");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Connection failed");//Provisional , acomodar bien con clase exceptions
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connection failed");//Provisional , acomodar bien con clase exceptions
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    /**
     * Checks out in the database if the acces credentials are valid.
     * @param user_gmail Player gmail account.
     * @param password Player password..
     * @return A String with an error message if either user or password where wrong or "NO" if 
     * the acces credentials where correct.
     * @see iquerys.
     * @see imessageForUser.
     * 
     */
    public String logInDB(String user_gmail, String password) {
        String check = NO;
        try {
            pStatement = connectionDB().prepareStatement(USERANDPASSWORDVERIFIED);
            pStatement.setString(1, user_gmail);
            pStatement.setString(2, password);
            pStatement.setString(3, user_gmail);
            pStatement.setString(4, password);
            resultset = pStatement.executeQuery();

            if (!resultset.next()) {
                check = INVALID_USR_PASS;
            }
            resultset.close();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, FILE_NOT_FOUND);
        }
        return check;
    }

    /**
     *
     * @return An Arraylist of String with all the nicknames registered in the data base.
     */
    public ArrayList<String> dataBaseToArray() {
        ArrayList<String> users = new ArrayList();
        
        try {
            pStatement = connectionDB().prepareStatement("SELECT * FROM BASE_USUARIOS.USUARIO");
            resultset = pStatement.executeQuery();

            while (resultset.next()) {
                users.add(resultset.getString("nickName"));
            }

            resultset.close();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, FILE_NOT_FOUND);
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Signs in a new player.
     * @param name Player name.
     * @param lastName Player lastname.
     * @param nickName Player nickname.
     * @param password Player password.
     * @param gmail Player gmail account.
     */
    public void signInDB(String name, String lastName, String nickName, String password, String gmail) {
        String query = INSERTUSER;
        try {
            pStatement = connectionDB().prepareStatement(query);
            pStatement.setString(3, name);
            pStatement.setString(4, lastName);
            pStatement.setString(1, nickName);
            pStatement.setString(2, password);
            pStatement.setString(5, gmail);
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Checks out in the data base if either the nickname or email account 
     * chosen by the new player are already used.
     * @param nickName Player nickname.
     * @param gmail Player gmail account.
     * @return "NO" if neither nickname or email are already used. <p>
     * "user" if nickname is already used.<p>
     * "gmail" if nickname is already used.
     */
    public String nicknameOrGmailUsed(String nickName, String gmail) { //agregar genericidad aca
        String check = NO;

        try {
            connection = connectionDB();
            pStatement = connection.prepareStatement(REALUSER);
            pStatement.setString(1, nickName);
            resultset = pStatement.executeQuery();

            if (resultset.next()) {
                check = " user ";
            }

            pStatement = connection.prepareStatement(REALGMAIL);
            pStatement.setString(1, gmail);
            resultset = pStatement.executeQuery();

            if (resultset.next()) {
                check = " gmail ";
            }
            resultset.close();
            connection.close();
            pStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, FILE_NOT_FOUND);
        }
        return check;
    }
}
