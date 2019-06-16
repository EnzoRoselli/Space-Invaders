package Game.player;

import Game.blocks.BasicBlocks;
import java.awt.Graphics2D;
import java.util.ArrayList;
import Game.sound.Sound;
import Game.timer.Timer;

/**
 * This class creates the player bullets.
 * @author InvadersTeam
 * @since March 2019
 */
public class PlayerWeapon {

    private Timer timer;
    private ArrayList<MachineGun> weapons = new ArrayList<MachineGun>();
    private Sound shootSound;

    /**
     * This constructor creates the timer and the bullets sounds.
     */
    public PlayerWeapon() {

        timer = new Timer();
        shootSound = new Sound("/Game/res/shoot.wav");
    }

    /**
     *
     * @return Arraylist with bullets.
     */
    public ArrayList<MachineGun> getWeapons() {
        return weapons;
    }

    /**
     * Draws player's bullets.
     * @param g
     */
    public void draw(Graphics2D g) {

        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).draw(g);
        }
    }

    /**
     * Updates player's bullets status.
     * @param delta Optimal speed.
     * @param blocks Foour blocks bunker.
     */
    public void update(double delta, BasicBlocks blocks) {

        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).update(delta, blocks); //al update de machineGun
            if (weapons.get(i).destroy()) {
                weapons.remove(i);
            }
        }
    }

    /**
     * Shoots the bullets from the machinegun's center.
     * @param xPos machinegun's center X coordinate.
     * @param yPos machinegun's center Y coordinate.
     * @param width player's bullet width.
     * @param height player's bullet height.
     */
    public void shootBullet(double xPos, double yPos, int width, int height) { //Posicion de las balas salen del medio de la nave, del cañon, y ancho y alto de las balas
        if (timer.timerEvent(500)) {
            if (shootSound.isPlaying()) {
                shootSound.stop();
            }
            shootSound.play();
            weapons.add(new MachineGun(xPos, yPos, width, height));
        }
    }
/**
 * Clears the player's bullets arraylist.
 */
    void reset() {
        weapons.clear();
    }
}//fin de la clase
