/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formula;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ksenija
 */
public class Simulate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        ArrayList<Venue> venues = new ArrayList<Venue>();
        
        ArrayList<Integer> accumulatedPoints = new ArrayList<Integer>();
        ArrayList<Integer> accumulatedTime = new ArrayList<Integer>();
        ArrayList<Integer> ranking = new ArrayList<Integer>();
        ArrayList<Boolean> eligibleToRace = new ArrayList<Boolean>();
           
        //object of Championship
        Championship championship = new Championship();
        
        //for first initializing 
        for(int i=0; i <  championship.getDrivers().size(); i++)
        {
            accumulatedPoints.add(0);
            eligibleToRace.add(Boolean.TRUE);
            accumulatedTime.add(0);
            ranking.add(championship.getDrivers().get(i).getRanking());
        }
 
        boolean right_number = false;
        int number_of_venues;
        
        //Choosing number of venues
        do
        {
            System.out.println("Odaberite koliko trka ce se voziti u sampionatu? (3-5)");
            number_of_venues = sc.nextInt();
            switch(number_of_venues)
            {
                case 3: 
                case 4:
                case 5: 
                        right_number = true;
                        break;
                default: 
                         System.out.println("Greska-Morate unijeti broj trka od 3 do 5.");  
                         break;
            }
        }while(right_number != true);
        
        //Starting champipnship
        for(int i=0; i < number_of_venues; i++)
        {
                System.out.println("Staze koje mogu biti odabrane:");
                for(int k=0; k<championship.getVenues().size(); k++)
                {
                    System.out.println(k +". " + championship.getVenues().get(k).getVenueName());
                }
                right_number = false;
                int number_of_venue;
                //Choosing venue
                do
                {
                    System.out.println("Unesite broj staze:");
                    number_of_venue = sc.nextInt();

                    if(number_of_venue < 0 || number_of_venue > championship.getVenues().size()-1)
                    {
                       System.out.println("Greska-Morate unijeti broj staze koja je navedena u tabeli"); 
                    }
                    else 
                    {
                       right_number = true;        
                    }
                }while(right_number != true);
                               
                //Prepare for the race :  INITIALIZATION     
                championship.prepareForTheRace();
                
                //Starting chosen venue
                for(int j = 1 ; j <= championship.getVenues().get(number_of_venue).getNumberOfLaps() ; j++)
                {
                    if(j == 2)
                    {
                        for(int m = 0; m < championship.getDrivers().size(); m++)
                        {
                            championship.getDrivers().get(m).pneumatic();
                        }
                    }
                    
                    championship.driveAverageLapTime(championship.getVenues().get(number_of_venue).getAverageLapTime()); 

                    championship.applySpecialSkills(j);

                    championship.checkMechanicalProblem();
                    
                    championship.checkRain(championship.getVenues().get(number_of_venue).getChanceOfRain());

                    championship.printLeader(j);
                }
                                
                championship.printWinnersAfterRace(championship.getVenues().get(number_of_venue).getVenueName());

                championship.getVenues().remove(number_of_venue);
        }
        championship.printChampion(number_of_venues);
    }         
}
