/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formula;

import java.util.Random;
/**
 *
 * @author ksenija
 */
public class RNG {
    private int minimumValue;
    private int maximumValue;
    private Random rnd;
    
    
    public RNG(int min, int max)
    {
        minimumValue = min;
        maximumValue = max;
    }
    
    public int getRandomValue() 
    {
        rnd = new Random();
        int difference = maximumValue - minimumValue;
        int rand_int = rnd.nextInt(difference);
        rand_int += minimumValue;
        return rand_int;
    }

 
    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }
    
    public int getMinimumValue() {
        return minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public Random getRnd() {
        return rnd;
    }
   
}
