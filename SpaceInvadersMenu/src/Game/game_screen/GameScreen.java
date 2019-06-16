package Game.game_screen;

import Controller.ScoreAndUserCatcher;
import Game.blocks.BasicBlocks;
import Game.player.Player;
import Game.display.Display;
import Game.enemy.EnemyBulletHandler;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import Game.levels.Level1;
import Game.timer.TickTimer;

/**
 * This class sets the game screen. It creates the player, the bunker, the bullets, score and enemies.
 * @author InvadersTeam
 * @since March 2019
 */
public class GameScreen {

    private Player player;
    private BasicBlocks blocks;
    private Level1 level;
    private static int SCORE = 0;
    private EnemyBulletHandler bulletHandler;

    private TickTimer gameOverTimer = new TickTimer(180);
    private TickTimer completeTimer = new TickTimer(180);
    private Font gameScreen = new Font("Arial", Font.PLAIN, 48);

    /**
     * This constructor creates the four bloks bunker, the player, the bullet handler and the level setting, which contains te enemies.
     */
    public GameScreen() {

        blocks = new BasicBlocks(); //los 4 bloques
        bulletHandler = new EnemyBulletHandler();
        player = new Player(Display.getWIDTH() / 2 - 50, Display.getHEIGHT() - 75, 50, 30, blocks);
        level = new Level1(player, bulletHandler);

    }

    /**
     *
     * @return The player score.
     */
    public static int getSCORE() {
        return SCORE;
    }

    /**
     * Assigns value to the screen score.
     * @param SCORE The player score.
     */
    public static void setSCORE(int SCORE) {
        GameScreen.SCORE = SCORE;
    }

    /**
     * Checks out if the game has ended.
     * @param delta Optimal speed.
     * @return True if game is finished.
     */
    public boolean update(double delta) {

        player.update(delta);
        level.update(delta, blocks);

        if (level.isGameOver(blocks)) {
            ScoreAndUserCatcher.setScore(SCORE);
            setSCORE(0);
            return true;
        }

        if (level.isComplete()) {
            completeTimer.tick(delta);
            if (completeTimer.isEventReady()) {
                level.reset();
            }
        }
        return false;
    }

    /**
     * Draws the whole screen. Regard the game status, the screen drawing will be different.
     * @param g
     * @return True if the player loose, false if the level was completed or if it is still playing.
     */
    public boolean draw(Graphics2D g) {

        g.setColor(Color.white);
        g.drawString("Score: " + getSCORE(), 5, 15);

        g.setColor(Color.red);
        g.drawString("Healt: " + player.getHealth(), 5, 35);

        player.draw(g);
        blocks.draw(g);
        level.draw(g);

        if (level.isGameOver(blocks)) {
            g.drawString("Score: " + 0, 5, 15);
            g.setColor(Color.red);
            g.setFont(gameScreen);
            String gameOver = "GAME OVER!";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
            g.drawString(gameOver, (Display.getWIDTH() / 2) - (gameOverWidth / 2), Display.getHEIGHT() / 2);
            return true;
        }

        if (level.isComplete()) {
            g.setColor(Color.green);
            g.setFont(gameScreen);
            String complete = "LEVEL COMPLETE!";
            int completeWidth = g.getFontMetrics().stringWidth(complete);
            g.drawString(complete, (Display.getWIDTH() / 2) - (completeWidth / 2), Display.getHEIGHT() / 2);
        }
        return false;
    }

    /**
     * Initiates the spaceship motion.
     * @param canvas
     */
    public void initCanvas(Canvas canvas) { //movilidad a la nave
        canvas.addKeyListener(player);
    }

}//fin de la clase
