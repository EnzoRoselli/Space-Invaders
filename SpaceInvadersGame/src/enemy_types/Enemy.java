package enemy_types;

import game_screen.BasicBlocks;
import game_screen.Player;
import handler.EnemyBulletHandler;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Enemy {

    private EnemyBulletHandler bulletHandler;
	
	public Enemy(EnemyBulletHandler bulletHandler) {
		this.bulletHandler = bulletHandler;
	}
    
    public abstract void draw(Graphics2D g);
    public abstract void update(double delta, Player player, BasicBlocks blocks);
    public abstract void changeDirection(double delta);

    public abstract boolean deathScene();
    public abstract boolean collidePlayerBullet(int i, Player player, ArrayList<Enemy> enemies);
    public abstract boolean collideEnemiesBlocks(int i,BasicBlocks blocks, ArrayList<Enemy> enemies);
    public abstract boolean isOutOfBounds();
    
    public EnemyBulletHandler getBulletHandler() {
		return bulletHandler;
	}
    
}
