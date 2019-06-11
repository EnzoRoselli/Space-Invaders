package display;

import View.Menu;
import state.StateMachine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import levels.Level1;

public class Display extends Canvas implements Runnable {

    private static int WIDTH = 800, HEIGHT = 600;
    private boolean running = false;
    private Thread thread;
    private static int frames = 0;
    private static StateMachine state;
    private Level1 level;
    private int FPS;
    private static Display show;
    private static Menu hi;

    public Display() {

        setSize(WIDTH, HEIGHT);
        setFocusable(true);

        state = new StateMachine(this);
        state.setState((byte) 0);

    }

    public static void main(String args[]) {
        executeGame();
    }
    
    public static void executeGame() {
        show = new Display();
        JFrame frame = new JFrame();
        frame.add(show);
        frame.pack(); //El tamaño se ajusta segun lo que haya adentro del marco
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //No se puede cambiar el tamaño una vez ejecutado
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //Centra el marco
        show.start();

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public synchronized void start() {

        if (!running) {

            running = true;

            thread = new Thread(this);
            thread.start();
        }

    }

    public synchronized void stop() {

        if (running) {

            running = false;

            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

    @Override
    public void run() {

        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime(); //nanoSegundos en ese instante
        final int TARGET_FPS = 60; //cuantas actualizaciones queremos por segundo
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; //cuantos nanosegundos por actualizacion
        int frames = 0;

        this.createBufferStrategy(3); //creo los buffers
        BufferStrategy bs = getBufferStrategy(); //se los asigno 

        while (running) {

            long now = System.nanoTime(); //nanoSegundos en segundo instante
            long updateLength = now - lastLoopTime; //diferencia de nanoSegundos en diferentes momentos
            lastLoopTime = now; //remplala lastLoop por el ultimo valor, para hacer la prox vuelta mas precisa
            double delta = updateLength / ((double) OPTIMAL_TIME);

            frames++;

            if (System.currentTimeMillis() - timer > 1000) { //si la diferencia entre ambos periodos es muy grande frames se resetea
                timer += 1000;
                FPS = frames;
                frames = 0;
                System.out.println(FPS);
            }

            draw(bs);
            update(delta);

            try {
                Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000); //duerme el hilo para q el juego se ejecute a 60fps
            } catch (Exception e) {
            };

            if (draw(bs) && update(delta)) {
                show.stop();
                System.exit(0);
            }
        }
    }

    public boolean draw(BufferStrategy bs) {

        boolean check = false;
        do {

            do {

                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);

                check = state.draw(g);

                g.dispose(); //libera recursos del sistema q use una vez terminada su tarea

            } while (bs.contentsRestored());

            bs.show(); //buffer visible

        } while (bs.contentsLost());

        return check;
    }

    public boolean update(double delta) {

        return state.update(delta);

    }

}
