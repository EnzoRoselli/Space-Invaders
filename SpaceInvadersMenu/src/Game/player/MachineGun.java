package Game.player;

import Game.display.Display;
import Game.blocks.BasicBlocks;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * This class shapes the player spaceship/machine gun.
 * @author InvadersTeam
 * @since March 2019
 */
public class MachineGun {

    private Rectangle bullet;
    private final double speed = 2.5d;
    private double xPos, yPos;
    private int width, height;

    /**
     * Assigns value to all the class attributes.
     * @param xPos Specified X coordinate where the machine gun is going to be created.
     * @param yPos Specified Y coordinate where the machine gun is going to be created.
     * @param width Machine gun width.
     * @param height Machine gun height.
     */
    public MachineGun(double xPos, double yPos, int width, int height) {
        this.setxPos(xPos); 
        this.setyPos(yPos);
        this.setWidth(width);
        this.setHeight(height);

        this.bullet = new Rectangle((int) getxPos(), (int) getyPos(), getWidth(), getHeight());
    }

    //GETTERS

    /**
     * Gets the X coordinate where the machine gun is.
     * @return An X coordinate.
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Gets the Y coordinate where the machine gun is.
     * @return A Y coordinate.
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Gets the machine gun width.
     * @return Machine gun width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the machine gun height.
     * @return Machine gun height.
     */
    public int getHeight() {
        return height;
    }

    //SETTERS

    /**
     * Sets the Y coordinate where the machine gun is going to be.
     * @param xPos X coordinate.
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets the Y coordinate where the machine gun is going to be.
     * @param yPos Y coordinate.
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    /**
     * Sets the machine gun width.
     * @param width Machine gun width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the machine gun height.
     * @param height Machine gun height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Draws the bullets.
     * @param g
     */
    public void draw(Graphics2D g) {
        if (bullet == null) {
            return;
        }

        g.setColor(Color.GREEN);
        g.fill(bullet);
    }

    /**
     * Tracks the bullet. If it hits the wall, it will damage the block.<p> 
     * If the bullet gets out of the screen bounds it dissapear.
     * @param delta Optimal speed.
     * @param blocks Four blocks Bunker
     */
    public void update(double delta, BasicBlocks blocks) {
        if (bullet == null) {
            return;
        }

        this.setyPos(getyPos() - (delta * speed));
        bullet.y = (int) this.getyPos();
        wallCollide(blocks); //destruye bloques cuando lo toca la bala
        isOutofBounds(); //destruye la bala cuando sale de la pantalla
    }

    
    /**
     * Checks out if the bullet collides a rectangle object. It could either be an invader or a block.
     * If it happens, the bullet is destroyed.
     * @param rect Any rectangle object.
     * @return True if the bullet intersects any rectangle.
     */
    public boolean collisionRect(Rectangle rect) {
        if (this.bullet == null) {
            return false;
        }

        if (bullet.intersects(rect)) {
            this.bullet = null;
            return true;
        }

        return false;
    }

    /**
     *
     * @param poly
     * @return
     */
    public boolean collisionPoly(Polygon poly) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Destroys a bullet.
     * @return True if bullet was destroyed.
     */
    public boolean destroy() {
        if (bullet == null) {
            return true;
        }

        return false;
    }

    /**
     * If a bullet hits the wall, the block gets damaged.
     * @param blocks Four blocks bunker.
     */
    protected void wallCollide(BasicBlocks blocks) {
        for (int i = 0; i < blocks.getWall().size(); i++) {
            if (bullet.intersects(blocks.getWall().get(i))) {
                blocks.getWall().remove(i);
                bullet = null;
                return;
            }
        }
    }

    /**
     * Destroy the bullet if it gets out of the screen bounds.
     */
    protected void isOutofBounds() {
        if (this.bullet == null) {
            return;
        }

        if (bullet.y < 0 || bullet.y > Display.getHEIGHT() || bullet.x < 0 || bullet.x > Display.getWIDTH()) {
            bullet = null;
        }
    }

}//fin de la clase
