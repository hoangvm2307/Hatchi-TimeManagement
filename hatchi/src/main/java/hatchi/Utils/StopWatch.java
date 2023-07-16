/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author hoangvmdeptrai
 */
public class StopWatch {

    /**
     * @param timePassed the timePassed to set
     */
    public void setTimePassed(int timePassed) {
        this.timePassed = timePassed;
    }
    private int startingTime = 600;
    private int duration = startingTime;
    private int timePassed = 0;
    private boolean running = false;
    
    public int getTimerMinutes() {
        return duration / 60;
    }
    
    public int getTimerSeconds() {
        return duration % 60;
    }
    
    public int getStartingTimerMinutes() {
        return startingTime / 60;
    }
    
    public int getStartingTimerSeconds() {
        return startingTime % 60;
    }
    
    public int getDuration() {
        return duration;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public boolean isRunning() {
        return running;
    }
    
    public void start() {
        running = true;
        timePassed = 0;
    }

    public void reset() {
        duration = startingTime;
        running = false;
    }
    
    public void refresh() {
        running = false;
        timePassed = 0;
        startingTime = 600;
        duration = startingTime;
    }
    
    public void addStartingTime(int seconds) {
        if (startingTime < 120*60) {
            startingTime += seconds;
            duration = startingTime;
        }
    }

    public void subtractStartingTime(int seconds) {
        if (startingTime > 5 * 60) {
            startingTime -= seconds;
            duration = startingTime;
        }
    }
    
    public void tick() {
        duration -= 1;
        timePassed += 1;
    }
}
