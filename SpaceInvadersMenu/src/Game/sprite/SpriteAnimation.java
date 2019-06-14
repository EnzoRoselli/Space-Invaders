package Game.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Game.timer.Timer;

/**
 *
 * @author InvadersTeam
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
     * Creates 
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
     *
     * @param delta
     */
    public void update(double delta) {
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
    public void stopAnimation() {
        loop = false;
        play = false;
    }

    /**
     * 
     */
    public void resetSprite() {
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
     * 
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
     * @return
     */
    public byte getCurrentSprite() {
        return currentSprite;
    }

    /**
     *
     * @param currentSprite
     */
    public void setCurrentSprite(byte currentSprite) {
        this.currentSprite = currentSprite;
    }

    /**
     *
     * @return
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     *
     * @param loop
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     *
     * @return
     */
    public boolean isSpriteAnimDestroyed() {
        if (sprites == null) //si todos los aliens fueron destruidos
        {
            return true;
        }

        return false;
    }

    /**
     *
     * @param spriteMap
     * @param xPos
     * @param yPos
     * @param width
     * @param height
     */
    public void addSprite(BufferedImage spriteMap, int xPos, int yPos,
            int width, int height) {
        sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
    }

    /**
     *
     * @param play
     * @param destoryAfterAnim
     */
    public void setPlay(boolean play, boolean destoryAfterAnim) {
        if (loop) {
            loop = false;
        }

        this.play = play;
        this.setDestroyAfterAnim(destoryAfterAnim);
    }

    /**
     *
     * @return
     */
    public double getxPos() {
        return xPos;
    }

    /**
     *
     * @param xPos
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     *
     * @return
     */
    public double getyPos() {
        return yPos;
    }

    /**
     *
     * @param yPos
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    /**
     *
     * @return
     */
    public boolean isDestroyAfterAnim() {
        return destroyAfterAnim;
    }

    /**
     * 
     * @param destroyAfterAnim
     */
    public void setDestroyAfterAnim(boolean destroyAfterAnim) {
        this.destroyAfterAnim = destroyAfterAnim;
    }

    /**
     *
     * @return sprite width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets sprite width.
     * @param width sprite width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return sprite height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets sprite height.
     * @param height sprite height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return Sprite speed.
     */
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    /**
     * Sets sprite animatin speed.
     * @param animationSpeed Sprite speed.
     */
    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    /**
     *
     * 0 = closed arms.
     * 1 = extended arms.
     * 2 = invasor explotion.
     * @return Invader arm position.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Changes invaders arm position from close to open and vice versa
     * @param limit Invader arm position.
     */
    public void setLimit(int limit) {
        if (limit > 0) {
            this.limit = limit - 1;
        } else {
            this.limit = limit;
        }
    }

    /**
     *
     */
    
    public void resetLimit() {
        limit = sprites.size() - 1;
    }

    /**
     *
     * @return
     */
    public boolean getPlay() {
        return play;
    }
}//fin de la clase
