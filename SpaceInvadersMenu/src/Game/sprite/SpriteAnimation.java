package Game.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Game.timer.Timer;

/**
 * this class allows to configure the enemy animation.
 * @author InvadersTeam
 * @since March 2019
 * 
 */
public class SpriteAnimation {

    private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
    private byte currentSprite;

    private boolean loop = false;
    private boolean play = false;
    private boolean destroyAfterAnim = false;

    private Timer timer;
    private int animationSpeed;
    private double xPos, yPos;
    private int width, height;
    private int limit;

    /**
     * Creates invaders animation.
     * @param xPos Specified X coordinate where the sprites are going to be created
     * @param yPos Specified Y coordinate where the sprites are going to be created
     * @param rows Sprite height
     * @param columns Sprite width 
     * @param animationSpeed Sprite's animation speed.
     * @param imgPath Sprite image URL.
     */
    public SpriteAnimation(double xPos, double yPos, int rows, int columns, int animationSpeed, String imgPath) {
        this.animationSpeed = animationSpeed;
        this.xPos = xPos;
        this.yPos = yPos;

        try {
            URL url = this.getClass().getResource(imgPath);
            BufferedImage pSprite = ImageIO.read(url);
            int spriteWidth = pSprite.getWidth() / columns;
            int spriteHeight = pSprite.getHeight() / rows;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    addSprite(pSprite, (x * spriteWidth), (y * spriteHeight), spriteWidth, spriteHeight);
                }
            }

        } catch (IOException e) {
        };

        timer = new Timer();
        limit = sprites.size() - 1;
    }
    
    
    
    
    

    /**
     * Draws sprites.
     *
     * @param g  
     */
    public void draw(Graphics2D g) {
        if (isSpriteAnimDestroyed()) {
            return;
        }

        g.drawImage(sprites.get(currentSprite), (int) getxPos(), (int) getyPos(), width, height, null);
    }

    
    
    
    
    /**
     * Updates invaders status.
     */
    public void update() { /// borro double delta como parametro
        if (isSpriteAnimDestroyed()) {
            return;
        }

        if (loop && !play) {
            loopAnimation();
        }
        if (play && !loop) {
            playAnimation();
        }
    }

    
    
    
    /**
     * Stops sprite animation
     */
    private void stopAnimation() {
        loop = false;
        play = false;
    }

    
    
    
    
    /**
     * 
     */
    private void resetSprite() {
        loop = false;
        play = false;
        currentSprite = 0;
    }
    
    
    
    
    
    /**
     * Loops sprite animation.
     */
    
    private void loopAnimation() {
        if (timer.isTimerReady(animationSpeed) && currentSprite == limit) {
            currentSprite = 0;
            timer.resetTimer();
        } else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
            currentSprite++;
        }
    }
    
    
    
    
    
    /**
     * Starts sprites animation.
     */
    
    private void playAnimation() {
        if (timer.isTimerReady(animationSpeed) && currentSprite != limit && !isDestroyAfterAnim()) {
            play = false;
            currentSprite = 0;
        } else if (timer.isTimerReady(animationSpeed) && currentSprite == limit && isDestroyAfterAnim()) {
            sprites = null;
        } else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
            currentSprite++;
        }
    }

    
    
    
    
    /**
     *
     * @return sprites index. 0, 1 or 2.
     */
    public byte getCurrentSprite() {
        return currentSprite;
    }

    
    
    
    /**
     * Assigns value to currentSprite 
     * @param currentSprite
     */
    public void setCurrentSprite(byte currentSprite) {
        this.currentSprite = currentSprite;
    }

    
    
    
    /**
     *
     * @return true true if animation loop is active.
     */
    public boolean isLoop() {
        return loop;
    }

    
    
    
    
    /**
     * 
     * @param loop true if animation loop is active.
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    
    
    
    /**
     * @return true if there is no invader left to destroy.
     */
    public boolean isSpriteAnimDestroyed() {
        if (sprites == null) //si todos los aliens fueron destruidos
        {
            return true;
        }

        return false;
    }

    
    
    
    /**
     * Adds a new invader to the map.
     * @param spriteMap image map.
     * @param xPos Specified X coordinate where the new invader is going to be added.
     * @param yPos Specified Y coordinate where the new invader is going to be added.
     * @param width new invader width.
     * @param height new invader height.
     */
    private void addSprite(BufferedImage spriteMap, int xPos, int yPos,
            int width, int height) {
        sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
    }

    
    
    
    /**
     * Stops loop animation and sets as true destroyAfterAnim and play.
     * @param play true if invader is destroyed.
     * @param destroyAfterAnim true if invader is destroyed.
     */
    public void setPlay(boolean play, boolean destroyAfterAnim) {
        if (loop) {
            loop = false;
        }

        this.play = play;
        this.setDestroyAfterAnim(destroyAfterAnim);
    }

    
    
    
    /**
     * Gets the X coordinate position.
     * @return An X coordinate position.
     */
    public double getxPos() {
        return xPos;
    }

    
    
    
    /**
     * Sets an X coordinate position.
     * @param xPos X coordinate position.
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    
    
    
    /**
     * Gets the Y coordinate position.
     * @return Y coordinate position
     */
    public double getyPos() {
        return yPos;
    }

    
    
    
    /**
     * Sets Y coordinate position.
     * @param yPos Y coordinate position.
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    
    
    
    /**
     *
     * @return True if invader is destroyed.
     */
    public boolean isDestroyAfterAnim() {
        return destroyAfterAnim;
    }

    
    
    
    /**
     * Sets invader's destruction status.
     * @param destroyAfterAnim True if it is destroyed.
     */
    public void setDestroyAfterAnim(boolean destroyAfterAnim) {
        this.destroyAfterAnim = destroyAfterAnim;
    }

    
    
    
    /**
     *
     * @return invader's width.
     */
    public int getWidth() {
        return width;
    }

    
    
    
    /**
     * Sets invader's width.
     * @param width invasor width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    
    
    
    /**
     * 
     * @return invader's height.
     */
    public int getHeight() {
        return height;
    }

    
    
    
    /**
     * Sets invader's height.
     * @param height invasor height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    
    
    
    /**
     *
     * @return invader's speed.
     */
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    
    
    
    /**
     * Sets invader's animation speed.
     * @param animationSpeed Sprite speed.
     */
    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    
    
    
    /**
     * Returns invader's arm position.

     * @return      
     * 0 = closed arms.<p>
     * 1 = extended arms.<p>
     * 2 = invasor explotion.
     * 
     */
    public int getLimit() {
        return limit;
    }

    
    
    
    /**
     * Changes invader's arm position from close to open and vice versa
     * @param limit Invader arm position.<p>
     * 0 = closed arms.<p>
     * 1 = extended arms.<p>
     * 2 = invasor explotion.
     */
    public void setLimit(int limit) {
        if (limit > 0) {
            this.limit = limit - 1;
        } else {
            this.limit = limit;
        }
    }

    
    
    
    /**
     * Sets invader's arms to the initial position.
     */
    
    public void resetLimit() {
        limit = sprites.size() - 1;
    }

    
    
    
    /**
     *
     * @return true if animation is playing.
     */
    public boolean getPlay() {
        return play;
    }
}//fin de la clase
