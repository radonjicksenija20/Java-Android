/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formula;

/**
 *
 * @author ksenija
 */
public class Venue {
        private int averageLapTime;
        private double chanceOfRain;
        private int numberOfLaps;
        private String venueName;
       
    
    public void setAverageLapTime(int averageLapTime) {
        this.averageLapTime = averageLapTime;
    }

    public void setChanceOfRain(double chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public void setNumberOfLaps(int numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getAverageLapTime() {
        return averageLapTime;
    }

    public double getChanceOfRain() {
        return chanceOfRain;
    }

    public int getNumberOfLaps() {
        return numberOfLaps;
    }

    public String getVenueName() {
        return venueName;
    }
        
        
        
}
