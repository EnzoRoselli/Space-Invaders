package Game.enemy;

import Game.blocks.BasicBlocks;
import Game.player.Player;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * This class allows to handle the bullet functions
 * @author InvadersTeam
 * @since March 2019
 */
public class EnemyBulletHandler {

    private ArrayList<EnemyBasicBullet> weaponTypes = new ArrayList<>();

    /**
     * Adds one bullet to the arraylist container.
     * @param weaponType
     */
    public void addBullet(EnemyBasicBullet weaponType) {
        this.weaponTypes.add(weaponType);
    }

    /**
     * Iterates the bullet arraylist, drawing all the objects in it.
     * @param g
     */
    public void draw(Graphics2D g) {
        for (EnemyBasicBullet enemyWeaponType : weaponTypes) {
            enemyWeaponType.draw(g);
        }
    }

    /**
     * Removes a bullet from the arraylist if it collides the bunker or the player.<p>
     * If the bullet hits the spaceship, player health will be decreased.
     * 
     * @param delta Optimal Speed.
     * @param blocks Four blocks bunker.
     * @param player Player object.
     */
    public void update(double delta, BasicBlocks blocks, Player player) {

        for (int i = 0; i < weaponTypes.size(); i++) {
            weaponTypes.get(i).update(delta, blocks);
            if (weaponTypes.get(i).collision(player.getRect())) {
                weaponTypes.remove(i);
                player.hit();
            }
        }
    }

    /**
     * Clears the bullet arraylist. 
     */
    public void reset() {
        weaponTypes.clear();
    }

}//end of class
