package Game.player;

import Game.display.Display;
import Game.blocks.BasicBlocks;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author InvadersTeam
 */
public class Player implements KeyListener {

    private final double speed = 5.0d;
    private int health;

    private BufferedImage pSprite;
    private double xPos, yPos, startXPos, startYPos;
    private int width, height;
    private Rectangle rect;
    private BasicBlocks blocks;
    private PlayerWeapon playerWeapons;

    private boolean left = false, right = false, shoot = false;

    /**
     *
     * @param xPos
     * @param yPos
     * @param width
     * @param height
     * @param blocks
     */
    public Player(double xPos, double yPos, int width, int height, BasicBlocks blocks) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.startXPos = xPos;
        this.startYPos = yPos;
        this.width = width;
        this.height = height;
        this.health = 3;

        rect = new Rectangle((int) xPos, (int) yPos + 5, width, height - 25);

        try {

            URL url = this.getClass().getResource("/Game/res/Player.png");
            pSprite = ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.blocks = blocks; //cuenta como los 4 bloques juntos
        playerWeapons = new PlayerWeapon();
    }

    /**
     *
     * @return
     */
    public PlayerWeapon getPlayerWeapon() {
        return playerWeapons;
    }

    /**
     *
     * @return
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics2D g) {

        g.drawImage(pSprite, (int) xPos, (int) yPos, width, height, null);
        playerWeapons.draw(g);
    }

    /**
     *
     * @param delta
     */
    public void update(double delta) { //esta a la escucha de cuando se apretan esas teclas

        if (right && !left && xPos < Display.getWIDTH() - width - 10) { //si se apreta derecha y si la nave no esta a 10 pixeles del borde

            xPos += speed * delta; //cambia la posicion de "x"
            rect.x = (int) xPos; //y el valor de "x" en la hitbox
        }
        if (!right && left && xPos > 10) {

            xPos -= speed * delta;
            rect.x = (int) xPos;
        }

        playerWeapons.update(delta, blocks);

        if (shoot) {
            playerWeapons.shootBullet(xPos + 22, yPos, 5, 15); //Posicion de las balas salen del medio de la nave, del cañon, y ancho y alto de las balas
        }
    }

    /**
     *
     */
    public void hit() {
        setHealth(getHealth() - 1);
    }

    /**
     *
     */
    public void reset() {
        health = 3;
        left = false;
        right = false;
        shoot = false;

        xPos = startXPos;
        yPos = startYPos;
        rect.x = (int) xPos;
        rect.y = (int) yPos + 25;
        playerWeapons.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        int key = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { //movimiento derecha
            right = true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { //movimiento izquierda
            left = true;
        }

        if (key == KeyEvent.VK_SPACE) {
            shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { //para de moverse derecha
            right = false;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { //para de moverse izquierda
            left = false;
        }

        if (key == KeyEvent.VK_SPACE) {
            shoot = false;
        }
    }

}//fin de la clase
