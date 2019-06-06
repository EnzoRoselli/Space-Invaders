
package levels;

import enemy_types.Enemy;
import enemy_types.EnemyBasic;
import game_screen.BasicBlocks;
import game_screen.Player;
import handler.EnemyBulletHandler;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Level1 implements SuperLevel{
    
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private EnemyBulletHandler bulletHandler;

        public Level1(Player player,EnemyBulletHandler bulletHandler){
		this.player = player;
                this.bulletHandler=bulletHandler;
                /*for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 10; x++) {
                        //Enemy e=new EnemyBasic(150 +(x*30), 50 + (y*30), 25, 25, "/images/Player.png");
                        Enemy e=new EnemyBasic(50, 50, 25, 25, "/images/Player.png");
                        enemies.add(e);
                    }
            }*/
                addEnemies();
	}
        
    @Override
    public void draw(Graphics2D g) {
        
        if(enemies==null)
            return;
        
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        bulletHandler.draw(g);
    }

    @Override
    public void update(double delta, BasicBlocks blocks) {
     
        if(enemies==null)
            return;
        
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(delta, player, blocks);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).collide(i, player, blocks, enemies);
        }
        hasDirectionChange(delta);
        bulletHandler.update(delta, blocks, player);
        
    }

    @Override
    public void hasDirectionChange(double delta) {
        
        if(enemies==null)
            return;
        
        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).isOutOfBounds()){
                changeDirectionAllEnemies(delta);
            }
        }
    }

    @Override
    public void changeDirectionAllEnemies(double delta) {
       
        for (int i = 0; i < 10; i++) {
            enemies.get(i).changeDirection(delta);
        }
    }
    
    public void addEnemies() {
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 10; x++){
				Enemy e = new EnemyBasic(150 + (x * 40), 25 + (y * 40), 1 , 3,bulletHandler);
				enemies.add(e);
			}
		}
	}

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public void destory() {
        
    }

    @Override
    public void reset() {
        
    }
}
