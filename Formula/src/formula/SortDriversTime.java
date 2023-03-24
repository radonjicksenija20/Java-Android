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
public class SortDriversTime implements Comparator<Driver> {
    
    public int compare(Driver a, Driver b)
    {
        return a.getAccumulatedTime() - b.getAccumulatedTime();
    }
    
}
