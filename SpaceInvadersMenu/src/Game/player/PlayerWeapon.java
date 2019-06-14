package Game.player;

import Game.blocks.BasicBlocks;
import java.awt.Graphics2D;
import java.util.ArrayList;
import Game.sound.Sound;
import Game.timer.Timer;

public class PlayerWeapon {

    private Timer timer;
    private ArrayList<MachineGun> weapons = new ArrayList<MachineGun>();
    private Sound shootSound;

    public PlayerWeapon() {

        timer = new Timer();
        shootSound = new Sound("/Game/res/shoot.wav");
    }

    public ArrayList<MachineGun> getWeapons() {
        return weapons;
    }

    public void draw(Graphics2D g) {

        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).draw(g);
        }
    }

    public void update(double delta, BasicBlocks blocks) {

        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).update(delta, blocks); //al update de machineGun
            if (weapons.get(i).destroy()) {
                weapons.remove(i);
            }
        }
    }

    public void shootBullet(double xPos, double yPos, int width, int height) { //Posicion de las balas salen del medio de la nave, del cañon, y ancho y alto de las balas
        if (timer.timerEvent(500)) {
            if (shootSound.isPlaying()) {
                shootSound.stop();
            }
            shootSound.play();
            weapons.add(new MachineGun(xPos, yPos, width, height));
        }
    }

    void reset() {
        weapons.clear();
    }
}//fin de la clase
