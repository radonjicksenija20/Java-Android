/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formula;
import java.util.Comparator;

/**
 *
 * @author ksenija
 */
public class Driver {
    
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace = true;
    private int accumulatedTime = 0;
    private int accumulatedPoints = 0;
    private boolean pneumatic = false;
       
    public void useSpecialSkill(RNG rng) {
                    int previous_time = accumulatedTime;
                    previous_time -= rng.getRandomValue();
                    accumulatedTime = previous_time;           
    }

    public void pneumatic(){
        
            RNG rng = new RNG(0, 2);  //for 50% of possibility - 0 or 1
            int possibility = rng.getRandomValue();
            if(possibility == 0)
            {
                pneumatic = false;
            }
            else
            {
                System.out.println("Vozac " + name + " je stavio pneumatike za kisu");
                pneumatic = true;
                accumulatedTime += 10;
            }
    }

    public boolean isPneumatic() {
        return pneumatic;
    } 

    public void setPneumatic(boolean pneumatic) {
        this.pneumatic = pneumatic;
    }
    
    public void setSpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setEligibleToRace(boolean eligibleToRace) {
        this.eligibleToRace = eligibleToRace;
    }

    public void setAccumulatedTime(int accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    public boolean isEligibleToRace() {
        return eligibleToRace;
    }

    public int getAccumulatedTime() {
        return accumulatedTime;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }
   
    
}