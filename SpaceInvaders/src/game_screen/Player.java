package game_screen;

import display.Display;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Player implements KeyListener {

    private final double speed = 5.0d;
    private BufferedImage pSprite;
    private double xPos, yPos;
    private int width, height;
    private Rectangle rect;
    private BasicBlocks blocks;
    private PlayerWeapon playerWeapons;

    private boolean left = false, right = false, shoot = false;

    public Player(double xPos, double yPos, int width, int height, BasicBlocks blocks) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        rect = new Rectangle((int) xPos, (int) yPos, width, height);

        try {

            URL url = this.getClass().getResource("/images/Player.png");
            pSprite = ImageIO.read(url);

        } catch (IOException e) {
        }

        this.blocks = blocks; //cuenta como los 4 bloques juntos
        playerWeapons = new PlayerWeapon();
    }

    public PlayerWeapon getPlayerWeapon(){
        return playerWeapons;
    }
    
    public void draw(Graphics2D g) {

        g.drawImage(pSprite, (int) xPos, (int) yPos, width, height, null);
        playerWeapons.draw(g);
    }

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
            playerWeapons.shootBullet(xPos+22, yPos, 5, 15); //Posicion de las balas salen del medio de la nave, del cañon, y ancho y alto de las balas
        }
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
        
        if (key == KeyEvent.VK_SPACE){
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
        
        if (key == KeyEvent.VK_SPACE){
			shoot = false;
		}
    }

}
