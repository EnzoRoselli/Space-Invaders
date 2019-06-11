package MVC.Exception;
import MVC.Interface.*;

/**
 *
 * @author Micael
 */
public class Exceptions extends Exception implements ImessageForUser{
    
    public Exceptions(/*String message*/){
        /*super(message);*/
    }
    
    public String connectionFailed() //Por ahora queda asi pero hay que hacerlo bien
    {       
    return CONNECTION_FAILED;
    }
}
