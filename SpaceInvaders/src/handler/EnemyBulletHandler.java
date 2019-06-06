
package handler;

import enemy_bullets.EnemyWeaponType;
import game_screen.BasicBlocks;
import game_screen.Player;
import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;


public class EnemyBulletHandler {

    private ArrayList<EnemyWeaponType> weaponTypes = new ArrayList<>();
	
	public void addBullet(EnemyWeaponType weaponType) {
		this.weaponTypes.add(weaponType);
	}

	public void draw(Graphics2D g) {
		for (EnemyWeaponType enemyWeaponType : weaponTypes) {
			enemyWeaponType.draw(g);
		}
	}
        
        public void update(double delta, BasicBlocks blocks, Player player) {
            
        }
}
