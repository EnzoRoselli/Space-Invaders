/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

/**
 *
 * @author usuario
 */
public class Timer {
    
    private long prevTime;
    
    public Timer(){
        
        setPrevTime(System.currentTimeMillis());
    }

    public long getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(long prevTime) {
        this.prevTime = prevTime;
    }
    
    public void resetTimer(){
        
        prevTime=System.currentTimeMillis();
    }
    
    public boolean timerEvent(int timer){
        
        if(System.currentTimeMillis()-getPrevTime()>timer){
            resetTimer();
            return true;
        }
        return false;
    }
    
    public boolean isTimerReady(int timer){
        
        if(System.currentTimeMillis()-getPrevTime()>timer){
            return true;
        }
        
        return false;
    }
}
