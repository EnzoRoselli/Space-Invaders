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

public class EnemyBasic {

    private double speed = 1.0d;
    private Rectangle rect;
    private SpriteAnimation enemySprite;
    private BufferedImage eSprite;
    private EnemyBulletHandler bulletHandler;

    private int shootTime;
    private Timer shootTimer;
    private Sound explosionSound;

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

    public Rectangle getRect() {
        return rect;
    }

    public EnemyBulletHandler getBulletHandler() {
        return bulletHandler;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }

    public void update(double delta, Player player, BasicBlocks blocks) {
        enemySprite.update(delta);

        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();

        if (shootTimer.timerEvent(shootTime)) {
            getBulletHandler().addBullet(new EnemyBasicBullet(getRect().x, getRect().y));
            shootTime = new Random().nextInt(12000);
        }
    }

    public void changeDirection(double delta) {

        speed *= -1.15d;
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();

        enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
        this.getRect().y = (int) enemySprite.getyPos();
    }

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

    public boolean collideEnemiesBlocks(int i, BasicBlocks blocks, ArrayList<EnemyBasic> enemies) {

        if (enemySprite.getPlay()) {
            if (enemies.get(i).deathScene()) {
                enemies.remove(i);
            }
            return false;
        }

        for (int b = 0; b < blocks.getWall().size(); b++) {

            if (rect.intersects(blocks.getWall().get(i))) {

                return true;
            }

        }

        return false;
    }

    public boolean isOutOfBounds() {

        if (rect.x > 0 && rect.x < Display.getWIDTH() - rect.width) {
            return false;
        }
        return true;
    }

}//fin de la clase