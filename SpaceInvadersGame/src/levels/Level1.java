package levels;

import enemy.EnemyBasic;
import blocks.BasicBlocks;
import player.Player;
import enemy.EnemyBulletHandler;
import java.awt.Graphics2D;
import java.util.ArrayList;
import sound.Sound;

public class Level1 {

    private Player player;
    private ArrayList<EnemyBasic> enemies = new ArrayList<>();
    private EnemyBulletHandler bulletHandler;
    private Sound beep, boop;
    private boolean beepboop;
    private EnemyBasic e;

    public Level1(Player player, EnemyBulletHandler bulletHandler) {
        this.player = player;
        this.bulletHandler = bulletHandler;
        addEnemies();

        beep = new Sound("/res/beep.wav");
        boop = new Sound("/res/boop.wav");
    }

    public void draw(Graphics2D g) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        bulletHandler.draw(g);
    }

    public void update(double delta, BasicBlocks blocks) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(delta, player, blocks);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).collidePlayerBullet(i, player, enemies);
        }
        hasDirectionChange(delta);
        bulletHandler.update(delta, blocks, player);

    }

    public void hasDirectionChange(double delta) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isOutOfBounds()) {
                changeDirectionAllEnemies(delta);
            }
        }
    }

    public void changeDirectionAllEnemies(double delta) {

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).changeDirection(delta);
        }

        if (beepboop) {
            beepboop = false;
            boop.play();
        } else {
            beepboop = true;
            beep.play();
        }

    }

    public void addEnemies() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 10; x++) {
                e = new EnemyBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler);
                enemies.add(e);
            }
        }
    }

    public boolean isGameOver(BasicBlocks blocks) {

        boolean check = false;

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies != null) {
                if (enemies.get(i).collideEnemiesBlocks(i, blocks, enemies)) {
                    check = true;
                }
            }
        }

        if (player.getHealth() <= 0) {
            check = true;
        }
        return check;
    }

    public boolean isComplete() {
        return enemies.isEmpty(); //Metodo de java, devuelve true si el array esta vacio
    }

    public void reset() {
        player.reset();
        enemies.clear();
        addEnemies();
        bulletHandler.reset();
    }
}//fin de la clase
