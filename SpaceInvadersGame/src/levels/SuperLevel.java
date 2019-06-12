package levels;

import game_screen.BasicBlocks;
import java.awt.Graphics2D;

public interface SuperLevel {

    void draw(Graphics2D g);

    void update(double delta, BasicBlocks blocks);

    void hasDirectionChange(double delta);

    void changeDirectionAllEnemies(double delta);

    boolean isGameOver(BasicBlocks blocks);

    boolean isComplete();

    void destory();

    void reset();
}
