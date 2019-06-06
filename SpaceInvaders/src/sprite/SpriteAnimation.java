package sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import timer.Timer;

public class SpriteAnimation {

    private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
    private byte currentSprite;

    public boolean loop = false;
    public boolean play = false;
    public boolean destroyAfterAnim = false;

    private Timer timer;
    private int animationSpeed;
    private double xPos, yPos;
    private int width, height;
    private int limit;

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
                    addSprite(pSprite, 0 + (x * spriteWidth), 0 + (y * spriteHeight),spriteWidth,spriteHeight);
                }
            }

        } catch (IOException e) {
        };

        timer = new Timer();
        limit=sprites.size()-1;
    }

    //Getters
    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getdestroyAfterAnim() {
        return destroyAfterAnim;
    }

    public byte getcurrentSprite() {
        return currentSprite;
    }

    public boolean getloop() {
        return loop;
    }
    
    public boolean getPlay() {
        return play;
    }
    
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public int getLimit() {
        return limit;
    }

    //Setters
    public void setxPos(double xPos) {

        this.xPos = xPos;
    }

    public void setyPos(double yPos) {

        this.yPos = yPos;
    }

    public void setcurrentSprite(byte currentSprite) {

        this.currentSprite = currentSprite;
    }

    public void setdestroyAfterAnim(boolean destroyAfterAnim) {
        this.destroyAfterAnim = destroyAfterAnim;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
    public void setWidth(int width){
        this.width=width;
    }
    
    public void setHeight(int height){
        this.height=height;
    }
    
    public void setPlay(boolean play) {
        this.play = play;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public void setLimit(int limit) {
        if(limit > 0) {
			this.limit = limit - 1;
		} else {
			this.limit = limit;
		}
    }
    
    public void resetLimit(){
        limit=sprites.size();
    }

    public void draw(Graphics2D g) {

       // BufferedImage eSprite=null;
        
        /*URL url = this.getClass().getResource("/images/Player.png");
        try {
            eSprite = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(SpriteAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (isSpriteAnimDestroyed()) {
            return;
        }*/

        g.drawImage(sprites.get(currentSprite), (int) getxPos(), (int) getyPos(), width, height,null);

        
    }

    public void update(double delta) {

        if (isSpriteAnimDestroyed()) {
            return;
        }

        if (loop && !play) {
            loopAnimation();
        }
        if (!loop && play) {
            playAnimation();
        }
    }

    public void stopAnimation() {

        loop = false;
        play = false;
        currentSprite=0;
    }

    public void resetSprite() {

        loop = false;
        play = false;
        currentSprite = 0;
    }

    private void loopAnimation() {

        if (timer.isTimerReady(animationSpeed) && currentSprite == limit) { //si esta en el final se resetea

            currentSprite = 0;
            timer.resetTimer();

        } else if (timer.timerEvent(animationSpeed) && currentSprite != limit) { //si no alcanza el final de la lista puede moverse al siguiente sprite

            currentSprite++;
        }

    }

    private void playAnimation() {

        if (timer.isTimerReady(animationSpeed) && currentSprite != limit && !isSpriteAnimDestroyed()) {

            play = false;
            currentSprite = 0;
        } else if (timer.isTimerReady(animationSpeed) && currentSprite == limit && isSpriteAnimDestroyed()) {

            sprites = null;
        } else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {

            currentSprite++;
        }

    }

    public boolean isSpriteAnimDestroyed() {

        boolean check = false;

        if (sprites == null) {
            check = true;
        }

        return check;
    }

    public void addSprite(BufferedImage spriteMap, int xPos, int yPos, int width, int height) {

        sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
    }

    public void setPlay(boolean play, boolean destroyAferAnim) {

        if(loop){
            loop=false;
        }
        
        this.play = play;
        this.destroyAfterAnim = destroyAferAnim;
    }

}
