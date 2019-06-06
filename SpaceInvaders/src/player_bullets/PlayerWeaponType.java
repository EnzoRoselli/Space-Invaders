package player_bullets;

import game_screen.BasicBlocks;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public abstract class PlayerWeaponType {

    private double xPos, yPos;
    private int width, height;

    public abstract void draw(Graphics2D g);

    public abstract void update(double delta, BasicBlocks blocks);

    public abstract boolean collisionRect(Rectangle rect);

    public abstract boolean collisionPoly(Polygon poly);

    public abstract boolean destroy();

    protected abstract void wallCollide(BasicBlocks blocks);

    protected abstract void isOutofBounds();

    //GETTERS
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

    //SETTERS
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
