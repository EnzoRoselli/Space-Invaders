package Game.enemy;

import Game.display.Display;
import Game.blocks.BasicBlocks;
import Game.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author InvadersTeam
 */
public class EnemyBasicBullet {

    private Rectangle bullet;
    private double speed = 2.5d;
    private int xPos, yPos;

    /**
     *
     * @param xPos
     * @param yPos
     */
    public EnemyBasicBullet(double xPos, double yPos) {
        bullet = new Rectangle((int) xPos, (int) yPos, 5, 10);
        setxPos((int) xPos);
        setyPos((int) yPos);
    }

    //Setters

    /**
     *
     * @param xPos
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     *
     * @param yPos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     *
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
     *
     * @param delta
     * @param blocks
     * @param player
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
     * @param rect
     * @return
     */
    public boolean collision(Rectangle rect) {
        if (bullet != null && bullet.intersects(rect)) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    public boolean destory() {
        return false;
    }

    /**
     *
     * @param blocks
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
     *
     */
    protected void isOutofBounds() {
        if (bullet != null && bullet.y < 0 || bullet.y > Display.getHEIGHT() || bullet.x < 0 || bullet.x > Display.getWIDTH()) {
            bullet = null;
        }
    }

    /**
     *
     * @return
     */
    public int getxPos() {
        return xPos;
    }

    /**
     *
     * @return
     */
    public int getyPos() {
        return yPos;
    }

}//fin de la clase
