package player;

import display.Display;
import blocks.BasicBlocks;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class MachineGun {

    private Rectangle bullet;
    private final double speed = 2.5d;
    private double xPos, yPos;
    private int width, height;

    public MachineGun(double xPos, double yPos, int width, int height) {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setWidth(width);
        this.setHeight(height);

        this.bullet = new Rectangle((int) getxPos(), (int) getyPos(), getWidth(), getHeight());
    }

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

    public void draw(Graphics2D g) {
        if (bullet == null) {
            return;
        }

        g.setColor(Color.GREEN);
        g.fill(bullet);
    }

    public void update(double delta, BasicBlocks blocks) {
        if (bullet == null) {
            return;
        }

        this.setyPos(getyPos() - (delta * speed));
        bullet.y = (int) this.getyPos();
        wallCollide(blocks); //destruye bloques cuando lo toca la bala
        isOutofBounds(); //destruye la bala cuando sale de la pantalla
    }

    public boolean collisionRect(Rectangle rect) {
        if (this.bullet == null) {
            return false;
        }

        if (bullet.intersects(rect)) {
            this.bullet = null;
            return true;
        }

        return false;
    }

    public boolean collisionPoly(Polygon poly) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean destroy() {
        if (bullet == null) {
            return true;
        }

        return false;
    }

    protected void wallCollide(BasicBlocks blocks) {
        for (int i = 0; i < blocks.getWall().size(); i++) {
            if (bullet.intersects(blocks.getWall().get(i))) {
                blocks.getWall().remove(i);
                bullet = null;
                return;
            }
        }
    }

    protected void isOutofBounds() {
        if (this.bullet == null) {
            return;
        }

        if (bullet.y < 0 || bullet.y > Display.getHEIGHT() || bullet.x < 0 || bullet.x > Display.getWIDTH()) {
            bullet = null;
        }
    }

}//fin de la clase
