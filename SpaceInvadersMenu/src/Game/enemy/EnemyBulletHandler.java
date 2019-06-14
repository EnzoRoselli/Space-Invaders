package Game.enemy;

import Game.blocks.BasicBlocks;
import Game.player.Player;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class EnemyBulletHandler {

    private ArrayList<EnemyBasicBullet> weaponTypes = new ArrayList<>();

    public void addBullet(EnemyBasicBullet weaponType) {
        this.weaponTypes.add(weaponType);
    }

    public void draw(Graphics2D g) {
        for (EnemyBasicBullet enemyWeaponType : weaponTypes) {
            enemyWeaponType.draw(g);
        }
    }

    public void update(double delta, BasicBlocks blocks, Player player) {

        for (int i = 0; i < weaponTypes.size(); i++) {
            weaponTypes.get(i).update(delta, blocks, player);
            if (weaponTypes.get(i).collision(player.getRect())) {
                weaponTypes.remove(i);
                player.hit();
            }
        }
    }

    public void reset() {
        weaponTypes.clear();
    }

}//end of class