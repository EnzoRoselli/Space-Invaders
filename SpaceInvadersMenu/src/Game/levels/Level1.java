package Game.levels;

import Game.enemy.EnemyBasic;
import Game.blocks.BasicBlocks;
import Game.player.Player;
import Game.enemy.EnemyBulletHandler;
import java.awt.Graphics2D;
import java.util.ArrayList;
import Game.sound.Sound;

/**
 * This class contains the level configuration. Enemies, player, bullets and sounds.
 * @author InvadersTeam
 * @since March 2019
 */
public class Level1 {

    private Player player;
    private ArrayList<EnemyBasic> enemies = new ArrayList<>();
    private EnemyBulletHandler bulletHandler;
    private Sound beep, boop;
    private boolean beepboop;
    private EnemyBasic e;

    /**
     * This constructor assigns a player, a bullet, .wav files to the sound attributes and it also adds enemies.
     * @param player Player which will be assigned.
     * @param bulletHandler Bullet Handler which will be assigned.
     */
    public Level1(Player player, EnemyBulletHandler bulletHandler) {
        this.player = player;
        this.bulletHandler = bulletHandler;
        addEnemies();

        beep = new Sound("/Game/res/beep.wav");
        boop = new Sound("/Game/res/boop.wav");
    }

    /**
     * Draws the enemies and their bullets.
     * @param g
     */
    public void draw(Graphics2D g) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        bulletHandler.draw(g);
    }

    /**
     * Updates enemies, player and bunker status. It also makes the invaders to change their direction.
     * @param delta Optimal speed.
     * @param blocks Four blocks bunker.
     */
    public void update(double delta, BasicBlocks blocks) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(delta, player, blocks);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).collidePlayerBullet(i, player, enemies);
        }
        hasDirectionChange(delta);
        bulletHandler.update(delta, blocks, player);

    }

    /**
     * Changes invaders direction when they get to  the screen limits.
     * @param delta Optimal speed.
     */
    public void hasDirectionChange(double delta) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isOutOfBounds()) {
                changeDirectionAllEnemies(delta);
            }
        }
    }

    /**
     * Change direction and sound of all invaders. 
     * @param delta Optimal speed.
     */
    public void changeDirectionAllEnemies(double delta) {

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).changeDirection(delta);
        }

        if (beepboop) {
            beepboop = false;
            boop.play();
        } else {
            beepboop = true;
            beep.play();
        }

    }

    /**
     * Adds enemies to the screen.
     */
    public void addEnemies() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 10; x++) {
                e = new EnemyBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler);
                enemies.add(e);
            }
        }
    }

    
    
    
    /**
     * Checks out if the player looses.
     * @param blocks Four blocks bunker.
     * @return True if the invaders get to the bunker or if player health gets to 0.
     */
    public boolean isGameOver(BasicBlocks blocks) {

        boolean check = false;

        for (int i = 0; i < enemies.size() ; i++) {
            if (enemies != null) {
                if (enemies.get(i).collideEnemiesBlocks(blocks, enemies)) {
                    return true;
                }
            }
        }

        if (player.getHealth() <= 0) {
            check = true;
        }
        return check;
    }

    /**
     * Checks if the player get to kill all invaders.
     * @return True if all invaders were destroyed.
     */
    public boolean isComplete() {
        return enemies.isEmpty(); //Metodo de java, devuelve true si el array esta vacio
    }

    /**
     * Resets the level.
     */
    public void reset() {
        player.reset();
        enemies.clear();
        addEnemies();
        bulletHandler.reset();
    }
}//fin de la clase
