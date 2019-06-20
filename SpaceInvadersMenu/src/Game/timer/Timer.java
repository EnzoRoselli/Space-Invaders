package Game.timer;

/**
 *
 * @author InvadersTeam
 * @since March 2019
 */
public class Timer {

    private long prevTime;

    /**
     * Constructor that asigns to a variable the current time in milliseconds.
     */
    public Timer() {

        setPrevTime(System.currentTimeMillis());
    }

    /**
     *
     * @return the current time.
     */
    public long getPrevTime() {
        return prevTime;
    }

    /**
     * Asigns to a variable the current time in milliseconds.
     * @param prevTime
     */
    public void setPrevTime(long prevTime) {
        this.prevTime = prevTime;
    }

    /**
     * Updates the variable to the current time this method is called.
     */
    public void resetTimer() {


        prevTime = System.currentTimeMillis();
    }

    /**
     * Call the function resetTimer() after a lapse of time that you can decide with the parameter variable.
     * @param timer
     * @return boolean;
     * @see resetTimer()
     */
    public boolean timerEvent(int timer) {

        if (System.currentTimeMillis() - getPrevTime() > timer) {
            resetTimer();
            return true;
        }
        return false;
    }

    /**
     * Checks the time you decided in the parameter variable happened.
     * @param timer
     * @return boolean;
     */
    public boolean isTimerReady(int timer) {

        if (System.currentTimeMillis() - getPrevTime() > timer) {
            return true;
        }

        return false;
    }
}//fin de la clase
