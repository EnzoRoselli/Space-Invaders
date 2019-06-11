package MVC;
import Controller.VisibleFramesHandler;
import View.*;

public class Main {
    public static void main(String args[]) {
        LogIn myLogin= new LogIn();
        VisibleFramesHandler visible = new VisibleFramesHandler();
        visible.visibleFrame(myLogin);
    }
}
