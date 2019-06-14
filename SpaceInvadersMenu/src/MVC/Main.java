package MVC;
import Controller.VisibleFramesHandler;
import View.*;

/**
 *
 * @author InvadersTeam
 */
public class Main {

    /**
     *
     */
    public VisibleFramesHandler visible;

    /**
     *
     * @param args
     */
    public static void main(String args[]) {       
        LogIn myLogin= new LogIn();
        VisibleFramesHandler visible = new VisibleFramesHandler();
        visible.visibleFrame(myLogin);
    }
}
