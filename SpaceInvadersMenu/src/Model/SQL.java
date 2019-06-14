package Model;

import MVC.Interface.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author InvadersTeam
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
     *
     * @return
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
     *
     * @param user_gmail
     * @param password
     * @return
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
     * @return
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
     *
     * @param name
     * @param lastName
     * @param nickName
     * @param password
     * @param gmail
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
     *
     * @param nickName
     * @param gmail
     * @return
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
