package Game.timer;

/**
 *
 * @author InvadersTeam
 */
public class Timer {

    private long prevTime;

    /**
     *
     */
    public Timer() {

        setPrevTime(System.currentTimeMillis());
    }

    /**
     *
     * @return
     */
    public long getPrevTime() {
        return prevTime;
    }

    /**
     *
     * @param prevTime
     */
    public void setPrevTime(long prevTime) {
        this.prevTime = prevTime;
    }

    /**
     *
     */
    public void resetTimer() {

        prevTime = System.currentTimeMillis();
    }

    /**
     *
     * @param timer
     * @return
     */
    public boolean timerEvent(int timer) {

        if (System.currentTimeMillis() - getPrevTime() > timer) {
            resetTimer();
            return true;
        }
        return false;
    }

    /**
     *
     * @param timer
     * @return
     */
    public boolean isTimerReady(int timer) {

        if (System.currentTimeMillis() - getPrevTime() > timer) {
            return true;
        }

        return false;
    }
}//fin de la clase
