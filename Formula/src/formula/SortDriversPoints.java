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
public class SortDriversPoints implements Comparator<Driver> {
 
    int direction = 1;
   
    public SortDriversPoints(int direction) {
		if(direction!=1 && direction!=-1){
			direction = 1;
		}
		this.direction = direction;
	}
    
    
    public int compare(Driver a, Driver b)
    {
         int retVal = 0;
         if(a!= null && b!=null){
			retVal = a.getAccumulatedPoints() - b.getAccumulatedPoints();
		}
         
         return retVal * direction;
    }
    
}
