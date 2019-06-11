package Controller;
public class Session {
    private String user_gmail;
    private String password;
    
    public Session(String user, String password) {
        this.user_gmail=user;
        this.password=password;
    }

    public String getUser_gmail() {
        return user_gmail;
    }

    public String getPassword() {
        return password;
    }
}
