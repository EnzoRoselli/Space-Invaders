package MVC;
import Controller.VisibleFramesHandler;
import View.*;

public class Main {
    public VisibleFramesHandler visible;
    public static void main(String args[]) {       
        LogIn myLogin= new LogIn();
        VisibleFramesHandler visible = new VisibleFramesHandler();
        visible.visibleFrame(myLogin);
    }
}
