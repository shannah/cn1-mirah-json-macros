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
public enum Seating {
    UNKNOWN("UNKNOWN", -1),
    LOTSOFROOM("LOTS OF ROOM", 0), 
    FEWPEOPLE("A FEW PEOPLE", 10), 
    PRETTYBUSY("PRETTY BUSY", 20), 
    FULL("FULL", 30);
    private String seatingStatus;
    private int seatingValue;
    
    Seating(String seatingStatus, int seatingValue) {
        this.seatingStatus = seatingStatus;
        this.seatingValue = seatingValue;
    }
    
    public String SeatingStatus() {
        return seatingStatus;
    }
    
    public int getSeatingValue() {
        return seatingValue;
    }

    @Override
    public String toString() {
        return SeatingStatus();
    }
    
    
    public static Seating getInstance(String status) {
        Seating seating = null;
        if (LOTSOFROOM.SeatingStatus().equals(status)) {
            seating = LOTSOFROOM;
        } else if (FEWPEOPLE.SeatingStatus().equals(status)) {
            seating = FEWPEOPLE;
        } else if (PRETTYBUSY.SeatingStatus().equals(status)) {
            seating = PRETTYBUSY;
        } else if (FULL.SeatingStatus().equals(status)) {
            seating = FULL;
        }
        return seating;
    }
    
    public static Seating getInstance(int value) {
        Seating seating = null;
        if (value < 10) {
            seating = LOTSOFROOM;
        } else if (value >= 10 && value < 20) {
            seating = FEWPEOPLE;
        } else if (value >= 20 && value < 30) {
            seating = PRETTYBUSY;
        } else {
            seating = FULL;
        }
        return seating;
    }
    
    
}
