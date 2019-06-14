package MVC.Interface;

/**
 *
 * @author InvadersTeam
 */
public interface Iquerys {

    /**
     *
     */
    public final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     *
     */
    public final String PORT = "jdbc:mysql://localhost:3306/base_usuarios?useSSL=false";

    /**
     *
     */
    public final String USER = "root";

    /**
     *
     */
    public final String PASSWORD = "0001";

    /**
     *
     */
    public final String USERANDPASSWORDVERIFIED = "SELECT * FROM base_usuarios.usuario WHERE (nickName=? AND password=?) OR (mail=? AND password=?)";

    /**
     *
     */
    public final String REALUSER = "SELECT * FROM base_usuarios.usuario WHERE nickName=?";

    /**
     *
     */
    public final String REALGMAIL = "SELECT * FROM base_usuarios.usuario WHERE mail=?";

    /**
     *
     */
    public final String INSERTUSER = "INSERT INTO base_usuarios.usuario (nickName, password, nombre, apellido, mail) VALUES (?,?,?,?,?);";
}
