package Game.timer;

/**
 * This class creates the timer mechanism.
 * @author InvadersTeam
 * @since March 2019
 */
public class TickTimer {

    private float tick, tickTarget;

    /**
     * 
     * @param tickTarget
     */
    public TickTimer(float tickTarget) {
        this.tickTarget = tickTarget;
        this.tick = 0;
    }

    /**
     * Increments tick by frame.
     * @param delta Optimal speed.
     */
    public void tick(double delta) {
        if (tick <= tickTarget) {
            tick += 1 * delta; //incrementa tick por el ultimo frame
        }
    }

    /**
     * Resets timer when it reaches the time limit.
     * @return true if reset.
     */
    public boolean isEventReady() {
        if (tick >= tickTarget) {
            resetTimer();
            return true;
        }
        return false;
    }
    
    
    /**
     * Resets timer.
     */
    private void resetTimer() {
        tick = 0;
    }
}//fin de la clase
