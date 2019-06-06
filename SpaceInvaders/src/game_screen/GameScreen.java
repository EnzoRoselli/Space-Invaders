
package game_screen;

import handler.EnemyBulletHandler;
import state.SuperStateMachine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import levels.Level1;


public class GameScreen implements SuperStateMachine{

    private Player player;
    private BasicBlocks blocks;
    private Level1 level;
    private static int SCORE=0;
    private EnemyBulletHandler bulletHandler;
    
    
    public GameScreen(){
        
        blocks = new BasicBlocks();
        bulletHandler=new EnemyBulletHandler();
        player=new Player(400, 500, 50, 35,blocks);
        level=new Level1(player,bulletHandler);
        
    }
    
    public static int getSCORE() {
        return SCORE;
    }
    
    @Override
    public void update(double delta) {
        
        player.update(delta);
        level.update(delta, blocks);
    }

    @Override
    public void draw(Graphics2D g) {
        
        g.setColor(Color.white);
        g.drawString("Score: "+SCORE, 5, 15);
        
        player.draw(g);
        blocks.draw(g);
    }

    @Override
    public void initCanvas(Canvas canvas) {
       canvas.addKeyListener(player);
    }
    
}
