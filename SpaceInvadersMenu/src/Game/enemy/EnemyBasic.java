package Game.enemy;

import Game.display.Display;
import Game.blocks.BasicBlocks;
import Game.game_screen.GameScreen;
import Game.player.Player;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import Game.sound.Sound;
import Game.sprite.SpriteAnimation;
import Game.timer.Timer;

/**
 * Constructor that creates a new Enemy object in an specified X,Y coordinate.
 *
 * @author InvadersTeam
 */
public class EnemyBasic {

    private double speed = 1.0d;
    private Rectangle rect;
    private SpriteAnimation enemySprite;
    private BufferedImage eSprite;
    private EnemyBulletHandler bulletHandler;

    private int shootTime;
    private Timer shootTimer;
    private Sound explosionSound;

    /**
     * Constructor that creates a new Enemy object in an specified X,Y coordinate.
     * 
     * @param xPos X Position where the Enemy object is going to be created.
     * @param yPos Y Position where the Enemy object is going to be created.
     * @param rows Enemy height.
     * @param columns Enemy width.
     * @param bulletHandler Creates and manage bullets 
     * @see EnemyBulletHandler.
     * 
     */
    public EnemyBasic(double xPos, double yPos, int rows, int columns, EnemyBulletHandler bulletHandler) {

        this.bulletHandler = bulletHandler;

        enemySprite = new SpriteAnimation(xPos, yPos, rows, columns, 300, "/Game/res/Invaders.png");

        enemySprite.setWidth(25);
        enemySprite.setHeight(25);
        enemySprite.setLimit(2);

        setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(), enemySprite.getWidth(), enemySprite.getHeight()));
        enemySprite.setLoop(true);

        shootTimer = new Timer();
        shootTime = new Random().nextInt(12000); //valor rand entre 0 y 12k
        explosionSound = new Sound("/Game/res/explosion.wav");
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
     * @return the bullet handler.
     */
    public EnemyBulletHandler getBulletHandler() {
        return bulletHandler;
    }

    /**
     *
     * @param rect
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Draws the enemies.
     * @param g 
     */
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }

    /**
     *
     * @param delta
     * @param player
     * @param blocks
     */
    public void update(double delta, Player player, BasicBlocks blocks) {
        enemySprite.update();

        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();

        if (shootTimer.timerEvent(shootTime)) {
            getBulletHandler().addBullet(new EnemyBasicBullet(getRect().x, getRect().y));
            shootTime = new Random().nextInt(12000);
        }
    }

    /**
     * Changes Invasors direction.
     * @param delta
     */
    public void changeDirection(double delta) {

        speed *= -1.15d;
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();

        enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
        this.getRect().y = (int) enemySprite.getyPos();
    }

    /**
     * Shows an invasor explotion.
     * @return true when explosion soound is played.
     */
    public boolean deathScene() {

        if (!enemySprite.getPlay()) {
            return false;
        }

        if (enemySprite.isSpriteAnimDestroyed()) {
            if (!explosionSound.isPlaying()) {
                explosionSound.play();
            }

            return true;
        }

        return false;
    }

    /**
     *
     * @param i Enemies index.
     * @param player Player figure.
     * @param enemies Arraylist of enemies.
     * @return
     */
    public boolean collidePlayerBullet(int i, Player player, ArrayList<EnemyBasic> enemies) {

        if (enemySprite.getPlay()) {
            if (enemies.get(i).deathScene()) {
                enemies.remove(i);
            }
            return false;
        }

        for (int w = 0; w < player.getPlayerWeapon().getWeapons().size(); w++) {
            if (enemies != null && player.getPlayerWeapon().getWeapons().get(w).collisionRect(((EnemyBasic) enemies.get(i)).getRect())) {

                enemySprite.resetLimit(); //elimina uno del arreglo de sprites
                enemySprite.setAnimationSpeed(120);//velocidad de la animacion de muerte
                enemySprite.setPlay(true, true);
                GameScreen.setSCORE(GameScreen.getSCORE() + 300);
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param blocks
     * @param enemies
     * @return
     */
    public boolean collideEnemiesBlocks(BasicBlocks blocks, ArrayList<EnemyBasic> enemies) {

        for (int b = 0; b < blocks.getWall().size(); b++) {
            if (rect.intersects(blocks.getWall().get(b))) {
                return true;
            }

        }

        return false;
    }

    /**
     *
     * @return true if it is out of the display bounds
     */
    public boolean isOutOfBounds() {

        if (rect.x > 0 && rect.x < Display.getWIDTH() - rect.width) {
            return false;
        }
        return true;
    }

}//fin de la clase
