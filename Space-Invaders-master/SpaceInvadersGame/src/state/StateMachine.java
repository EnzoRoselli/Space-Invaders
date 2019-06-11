package state;

import game_screen.GameScreen;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class StateMachine {

    private ArrayList<SuperStateMachine> states = new ArrayList<SuperStateMachine>();
    private Canvas canvas;
    private int selectState = 0;
    SuperStateMachine game;

    public StateMachine(Canvas canvas) {

        SuperStateMachine game = new GameScreen();
        states.add(game);
        this.canvas = canvas;
    }

    public boolean draw(Graphics2D g) {

        return states.get(selectState).draw(g);

    }

    public boolean update(double delta) {

        return states.get(selectState).update(delta);
    }

    public void setState(byte i) {

        for (int j = 0; j < canvas.getKeyListeners().length; j++) {

            canvas.removeKeyListener(canvas.getKeyListeners()[j]);
        }
        selectState = i;
        states.get(selectState).initCanvas(canvas);
    }

    public int getState() {

        return selectState;
    }

}
