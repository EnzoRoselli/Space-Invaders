
package state;

import java.awt.Canvas;
import java.awt.Graphics2D;


public interface SuperStateMachine {
    
    
    public boolean update(double delta);
    public boolean draw(Graphics2D g);
    public void initCanvas(Canvas canvas);


}
