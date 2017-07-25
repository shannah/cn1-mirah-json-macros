package ca.weblite.cn1.mirah.json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Chad
 */
public enum WaitTime {
    UNKNOWN("UNKNOWN", -1),
    NOWAIT("NO WAITING", 0),
    FIVETOTENMIN("5 - 10 MIN", 10),
    TENTOTWENTYMIN("10 - 20 MIN", 20),
    OVER20MIN("OVER 20 MIN", 30);

    private String waitTime;
    private int minutes;

    WaitTime(String waitTime, int minutes) {
        this.waitTime = waitTime;
        this.minutes = minutes;
    }
    
    
    public String getWaitTime() {
        return this.waitTime;
    }

    @Override
    public String toString() {
        return this.waitTime;
    }

    public int getMinutes() {
        return minutes;
    }
    
    public static WaitTime getInstance(int minutes) {
        WaitTime waitTime= null;
        
        if (minutes < 4) {
            waitTime = NOWAIT;
        } else if (minutes <= 10) {
            waitTime = FIVETOTENMIN;
        } else if (minutes <= 20 ) {
            waitTime = TENTOTWENTYMIN;
        } else {
            waitTime = OVER20MIN;
        }
        
        return waitTime;
    }
    

}
