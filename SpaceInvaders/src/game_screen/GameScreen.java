package game_screen;

import display.Display;
import handler.EnemyBulletHandler;
import state.SuperStateMachine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import levels.Level1;
import state.StateMachine;
import timer.TickTimer;

public class GameScreen extends SuperStateMachine {

    private Player player;
    private BasicBlocks blocks;
    private Level1 level;
    private static int SCORE = 0;
    private EnemyBulletHandler bulletHandler;

    private TickTimer gameOverTimer = new TickTimer(180);
    private TickTimer completeTimer = new TickTimer(180);
    private Font gameScreen = new Font("Arial", Font.PLAIN, 48);

    public GameScreen(StateMachine stateMachine) {

        super(stateMachine);
        blocks = new BasicBlocks();
        bulletHandler = new EnemyBulletHandler();
        player = new Player(400, 500, 50, 35, blocks);
        level = new Level1(player, bulletHandler);

    }

    public static int getSCORE() {
        return SCORE;
    }

    @Override
    public void update(double delta) {

        player.update(delta);
        level.update(delta, blocks);

        if (level.isGameOver()) {
            gameOverTimer.tick(delta);
            if (gameOverTimer.isEventReady()) {
                level.reset();
                blocks.reset();
                getStateMachine().setState((byte) 0);
                SCORE = 0;
            }
        }

        if (level.isComplete()) {
            completeTimer.tick(delta);
            if (completeTimer.isEventReady()) {
                level.reset();
            }
        }

    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.white);
        g.drawString("Score: " + SCORE, 5, 15);

        g.setColor(Color.red);
        g.drawString("Healt: " + player.getHealt(), 5, 35);

        player.draw(g);
        blocks.draw(g);

        if (level.isGameOver()) {
            g.setColor(Color.red);
            g.setFont(gameScreen);
            String gameOver = "GAME OVER!";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
            g.drawString(gameOver, (Display.getWIDTH() / 2) - (gameOverWidth / 2), Display.getHEIGHT() / 2);
        }

        if (level.isComplete()) {
            g.setColor(Color.green);
            g.setFont(gameScreen);
            String complete = "LEVEL COMPLETE!";
            int completeWidth = g.getFontMetrics().stringWidth(complete);
            g.drawString(complete, (Display.getWIDTH() / 2) - (completeWidth / 2), Display.getHEIGHT() / 2);
        }
        
    }

    @Override
    public void initCanvas(Canvas canvas
    ) {
        canvas.addKeyListener(player);
    }

}
