package enemy_types;

import display.Display;
import enemy_bullets.EnemyBasicBullet;
import game_screen.BasicBlocks;
import game_screen.GameScreen;
import game_screen.Player;
import handler.EnemyBulletHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import sound.Sound;
import sprite.SpriteAnimation;
import timer.Timer;

public class EnemyBasic extends Enemy {

    private double speed = 1.0d;
    private Rectangle rect;
    private SpriteAnimation enemySprite;
    private BufferedImage eSprite;
    
    private int shootTime;
    private Timer shootTimer;
    private Sound explosionSound;

    public EnemyBasic(double xPos, double yPos, int rows, int columns,EnemyBulletHandler bulletHandler) {

        super(bulletHandler);
        
        enemySprite = new SpriteAnimation(xPos, yPos, rows, columns, 300, "/images/Invaders.png");

        enemySprite.setWidth(25);
        enemySprite.setHeight(25);
        enemySprite.setLimit(2);

        setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(), enemySprite.getWidth(), enemySprite.getHeight()));
        enemySprite.setLoop(true);
        
        shootTimer=new Timer();
        shootTime=new Random().nextInt(12000); //valor rand entre 0 y 12k
        explosionSound=new Sound("/sounds/explosion.wav");
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }

    @Override
    public void update(double delta, Player player, BasicBlocks blocks) {
        enemySprite.update(delta);

        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();
        
        if(shootTimer.timerEvent(shootTime)){
            getBulletHandler().addBullet(new EnemyBasicBullet(getRect().x, getRect().y));
            shootTime= new Random().nextInt(12000);
        }
    }

    @Override
    public void changeDirection(double delta) {

        speed *= -1.15d;
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();

        enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
        this.getRect().y = (int) enemySprite.getyPos();
    }

    @Override
    public boolean deathScene() {

        if (!enemySprite.getPlay()) {
            return false;
        }

        if (enemySprite.isSpriteAnimDestroyed()) {
            if(!explosionSound.isPlaying())
            explosionSound.play();
            
            return true;
        }

        return false;
    }

    @Override
    public boolean collide(int i, Player player, BasicBlocks blocks, ArrayList<Enemy> enemys) {

        if (enemySprite.getPlay()) {
            if (enemys.get(i).deathScene()) {
                enemys.remove(i);
            }
            return false;
        }

        for (int w = 0; w < player.getPlayerWeapon().getWeapons().size(); w++) {
            if (enemys != null && player.getPlayerWeapon().getWeapons().get(w).collisionRect(((EnemyBasic) enemys.get(i)).getRect())) {

                enemySprite.resetLimit();
                enemySprite.setAnimationSpeed(120);//velocidad de la animacion de muerte
                enemySprite.setPlay(true, true);
                GameScreen.getSCORE();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isOutOfBounds() {

        if(rect.x > 0 && rect.x < Display.getWIDTH() - rect.width)
			return false;
		return true;
    }

}
