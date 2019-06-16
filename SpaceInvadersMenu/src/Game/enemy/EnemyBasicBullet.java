package Game.enemy;

import Game.display.Display;
import Game.blocks.BasicBlocks;
import Game.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class draws a bullet
 * @author InvadersTeam
 * @since March 2019
 */
public class EnemyBasicBullet {

    private Rectangle bullet;
    private double speed = 2.5d;
    private int xPos, yPos;

    /**
     * The constructor creates a new bullet in an specified X,Y coordinates.
     * @param xPos Specified X coordinate where the bullet is going to be created
     * @param yPos Specified Y coordinate where the bullet is going to be created     
     */
    public EnemyBasicBullet(double xPos, double yPos) {
        bullet = new Rectangle((int) xPos, (int) yPos, 5, 10);
        setxPos((int) xPos);
        setyPos((int) yPos);
    }

    //Setters

    /**
     * Sets an specified X coordinates as a bullet position.
     * @param xPos Specified X coordinate where the bullet is going to be created 
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets an specified Y coordinates as a bullet position.
     * @param yPos Specified Y coordinate where the bullet is going to be created 
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Draws a bullet.
     * @param g
     */
    public void draw(Graphics2D g) {
        if (bullet == null) {
            return;
        }

        g.setColor(Color.RED);
        g.fill(bullet);
    }

    /**
     * Updates bullet status.
     * @param delta optimal speed.
     * @param blocks Four blocks bunker.
     * @param player Spaceship.
     */
    public void update(double delta, BasicBlocks blocks, Player player) {

        if (bullet == null) {
            return;
        }

        setyPos((int) (getyPos() + (delta * speed)));
        bullet.y = getyPos();

        isOutofBounds();
        wallCollide(blocks);
    }

    /**
     *
     * @param rect hitbox. Could either be a bunker block or player.
     * @return True if bullet collides any rectangle.
     */
    public boolean collision(Rectangle rect) {
        if (bullet != null && bullet.intersects(rect)) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return Bullet destruction status.<p>
     * True if destroyed.
     */
    public boolean destroy() {
        return false;
    }

    /**
     * If a bullets collides the wall, a block will be removed.
     * @param blocks Four blocks bunker.
     */
    protected void wallCollide(BasicBlocks blocks) {
        if (bullet == null) {
            return;
        }

        for (int w = 0; w < blocks.getWall().size(); w++) {
            if (bullet.intersects(blocks.getWall().get(w))) {
                blocks.getWall().remove(w);
                bullet = null;
                break;
            }
        }
    }

    /**
     * Erase the bullet if it is out of the display bounds.
     */
    protected void isOutofBounds() {
        if (bullet != null && bullet.y < 0 || bullet.y > Display.getHEIGHT() || bullet.x < 0 || bullet.x > Display.getWIDTH()) {
            bullet = null;
        }
    }

    /**
     *
     * @return X coordinate where the bullet is located.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     *
     * @return Y coordinate where the bullet is located.
     */
    public int getyPos() {
        return yPos;
    }

}//fin de la clase
