package Game.enemy;

import Game.display.Display;
import Game.blocks.BasicBlocks;
import Game.game_screen.GameScreen;
import Game.player.Player;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import Game.sound.Sound;
import Game.sprite.SpriteAnimation;
import Game.timer.Timer;

/**
 * EnemyBasic class allows to create, update and draw the game enemies.
 * @author InvadersTeam
 * @since March 2019
 * 
 */
public class EnemyBasic {

    private double speed = 1.0d;
    private Rectangle rect;
    private SpriteAnimation enemySprite;
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
     * @return invader rectangle/hitbox.
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
     * Assigns a Rectangle hitbox to rect attribute.
     * @param rect invader hitbox.
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Draws the invaders.
     * @param g 
     */
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }

    /**
     * Updates invaders status.
     * @param delta frame reset auxiliar.
     * @param player Spaceship.
     * @param blocks Four blocks Bunker.
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
     * Changes Invaders direction.
     * @param delta optimal speed.
     */
    public void changeDirection(double delta) {

        speed *= -1.15d;
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();

        enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
        this.getRect().y = (int) enemySprite.getyPos();
    }

    /**
     * Shows an invader explosion.
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
     * Destroys an invader when hitted by a bullet and increases player score.
     * @param i Enemies index.
     * @param player Player figure.
     * @param enemies Arraylist of enemies.
     * @return True if the invader collides a player's bullet
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

                enemySprite.resetLimit();
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
     * Checks out if the invaders collide the bunker.
     * @param blocks Four blocks bunker.
     * @param enemies Invaders army arraylist.
     * @return True if invaders collide with the bunker.
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
     * @return true if it is out of the display's panel size 
     */
    public boolean isOutOfBounds() {

        if (rect.x > 0 && rect.x < Display.getWIDTH() - rect.width) {
            return false;
        }
        return true;
    }

}//fin de la clase
