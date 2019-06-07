/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_screen;

import display.Display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import state.StateMachine;
import state.SuperStateMachine;

/**
 *
 * @author usuario
 */
public class MenuScreen extends SuperStateMachine implements KeyListener{

    private Font tittleFont = new Font("Arial", Font.PLAIN, 64);
    private Font startFont = new Font("Arial", Font.PLAIN, 32);
    private String tittle = "Space Invaders";
    private String start = "Press Enter";

    public MenuScreen(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(tittleFont);
        int tittleWidth = g.getFontMetrics().stringWidth(tittle);
        g.setColor(Color.yellow);
        g.drawString(tittle, ((Display.getWIDTH() / 2) - (tittleWidth / 2)) - 2, (Display.getHEIGHT() / 2) - 123);
        g.setColor(Color.green);
        g.drawString(tittle, (Display.getWIDTH() / 2) - (tittleWidth / 2), (Display.getHEIGHT() / 2) - 125);

        g.setFont(startFont);
        g.setColor(Color.white);
        int startWidth = g.getFontMetrics().stringWidth(start);
        g.drawString(start, (Display.getWIDTH() / 2) - (startWidth / 2), (Display.getHEIGHT() / 2) + 75);
    }

    @Override
    public void initCanvas(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getStateMachine().setState((byte) 1);
		}
    }

}
