
package enemy;

import display.Display;
import blocks.BasicBlocks;
import player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class EnemyBasicBullet {

    private Rectangle bullet;
    private double speed = 2.5d;
    private int xPos, yPos;

    public EnemyBasicBullet(double xPos, double yPos) {
        bullet = new Rectangle((int) xPos, (int) yPos, 5, 10);
        setxPos((int) xPos);
        setyPos((int) yPos);
    }

    
    //Setters
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    
    public void draw(Graphics2D g) {
        if (bullet == null) {
			return;
		}
		
		g.setColor(Color.RED);
		g.fill(bullet);
    }

    
    public void update(double delta, BasicBlocks blocks, Player player) {
        
        if (bullet == null) {
			return;
		}
        
        setyPos((int) (getyPos() + (delta * speed))); 
		bullet.y = getyPos();
		
		isOutofBounds();
		wallCollide(blocks);
    }

    
    public boolean collision(Rectangle rect) {
        if(bullet !=null && bullet.intersects(rect)){
            return true;
        }
        
        return false;
    }

    
    public boolean destory() {
        return false;
    }

    
    protected void wallCollide(BasicBlocks blocks) {
       if (bullet == null) {
			return;
		}
		
		for (int w = 0; w < blocks.getWall().size(); w++) {
			if(bullet.intersects(blocks.getWall().get(w))) {
				blocks.getWall().remove(w);
				bullet = null;
				break;
			}
		}
    }

    
    protected void isOutofBounds() {
        if(bullet != null && bullet.y < 0 || bullet.y > Display.getHEIGHT() || bullet.x < 0 || bullet.x > Display.getWIDTH()){
			bullet = null;
		}
    }

    
    public int getxPos() {
        return xPos;
    }

    
    public int getyPos() {
        return yPos;
    }

}
