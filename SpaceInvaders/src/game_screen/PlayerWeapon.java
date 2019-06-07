package game_screen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import player_bullets.MachineGun;
import player_bullets.PlayerWeaponType;
import sound.Sound;
import timer.Timer;

public class PlayerWeapon {

    private Timer timer;
    private ArrayList<PlayerWeaponType> weapons = new ArrayList<PlayerWeaponType>();
    private Sound shootSound;

    public PlayerWeapon() {

        timer = new Timer();
        shootSound=new Sound("/sounds/shoot.wav");
    }
    
    public ArrayList<PlayerWeaponType> getWeapons(){
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
            if(shootSound.isPlaying()){
                shootSound.stop();
            }
            weapons.add(new MachineGun(xPos, yPos, width, height));
        }
    }

    void reset() {
        weapons.clear();
    }
}
