/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formula;

import java.util.ArrayList;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Collections;
/**
 *
 * @author ksenija
 */
public class Championship{
    ArrayList<Driver> drivers = new ArrayList<Driver>();
    ArrayList<Venue> venues = new ArrayList<Venue>();
    
    final int MINOR_MECHANICAL_FAULT = 5 ;
    final int MAJOR_MECHANICAL_FAULT = 3 ;
    final int UNRECOVERABLE_MECHANICAL_FAULT = 1 ;
    final int BIG_VALUE = 10000 ;
    
    
    String sP = System.getProperty("file.separator");
    String line;
   
    ArrayList<ArrayList<String>> data_venue = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> data_driver = new ArrayList<ArrayList<String>>();
    
    File driver_f = new File("." + sP + "vozaci.txt");
    File venue_f = new File("." + sP + "staze.txt");

    public Championship() throws IOException
    {
        //Create BufferedReader to read from the file
        BufferedReader bufferedReader_driver = new BufferedReader(new FileReader(driver_f));
        BufferedReader bufferedReader_venue = new BufferedReader(new FileReader(venue_f));
        
        ArrayList<String> driver_str = new ArrayList<String>();
        ArrayList<String> venue_str = new ArrayList<String>();
        
        while ((line = bufferedReader_driver.readLine()) != null)
            {           
                  driver_str.add(line);
            } 
        
        while ((line = bufferedReader_venue.readLine()) != null)
            {           
                  venue_str.add(line);
            } 
        
        
        for(int i=0; i<venue_str.size(); i++)
        {
            String[] venue_split = venue_str.get(i).split(",");         
            ArrayList<String> row = new ArrayList<String>();
            for (String s : venue_split) {
                row.add(s);
            }
            if(row.size() != 4)
            {
                System.out.println("Netacan broj informacija u fajlu staze");
                System.exit(0);
            }
            data_venue.add(row);
        }
        
        for(int i=0; i<driver_str.size(); i++)
        {
            String[] driver_split = driver_str.get(i).split(",");         
            ArrayList<String> row = new ArrayList<String>();
            for (String s : driver_split) {
                row.add(s);
            }
            if(row.size() != 3)
            {
                System.out.println("Netacan broj informacija u fajlu vozaca");
                System.exit(0);
            }
            data_driver.add(row);
        }
        
        
       for (ArrayList<String> row : data_venue) {
                
                    Venue v =  new Venue();
                    v.setVenueName(row.get(0));
                    v.setNumberOfLaps(Integer.parseInt(row.get(1)));
                    v.setAverageLapTime(Integer.parseInt(row.get(2)));
                    v.setChanceOfRain(Double.parseDouble(row.get(3)));
                   
                    venues.add(v);
        }
       
       for (ArrayList<String> row : data_driver) {
                
                    Driver d =  new Driver();                  
                    d.setName(row.get(0));
                    d.setRanking(Integer.parseInt(row.get(1)));
                    d.setSpecialSkill(row.get(2));
                   
                    drivers.add(d);
        }
        
    } 
    
    
    public void prepareForTheRace()
    {  
        
        for(int i=0; i<drivers.size(); i++)
         {
            // System.out.println("Priprema se za trku igrac " + drivers.get(i).getName());
              if(drivers.get(i).isEligibleToRace() == false)
              {
                  System.out.println("Igrac " + drivers.get(i).getName() + " je ispao iz trke, zato su preostali igraci:");
                  drivers.remove(i);
                  for(int j=0; j<drivers.size(); j++)
                  { 
                    System.out.println( j + ". " + drivers.get(j).getName());
                  }
              }
              else
              {
                    //Always without pneumatics at the beggining
                    drivers.get(i).setPneumatic(false);
                    
                    int previous_time =  drivers.get(i).getAccumulatedTime();
                    switch(drivers.get(i).getRanking())
                    {
                        case 1: 
                                  break;
                        case 2: 
                                  previous_time += 3;
                                  break;
                        case 3: 
                                  previous_time += 5;
                                  break;
                        case 4: 
                                  previous_time += 7;
                                  break;
                        default:
                                  previous_time += 10;
                                  break;
                    }    
                    drivers.get(i).setAccumulatedTime(previous_time);
                   // System.out.println("Igrac " + drivers.get(i).getName() + " ima poena: " + drivers.get(i).getAccumulatedPoints() + ". Rangiran je kao " + drivers.get(i).getRanking()+ ".  a akumulirano vrijeme mu je: " + drivers.get(i).getAccumulatedTime());
              }
        } 
    }
    
     public void driveAverageLapTime(int timeForLap)
     {
         for(int i=0; i<drivers.size(); i++)
         {
             int previous_time = drivers.get(i).getAccumulatedTime();
             previous_time += timeForLap;
             drivers.get(i).setAccumulatedTime(previous_time);
         }
     }
    
     
     public void applySpecialSkills(int number_of_laps)
     {
         for(int i=0; i<drivers.size(); i++)
         {
             if(drivers.get(i).getSpecialSkill().equals("Cornering")  || drivers.get(i).getSpecialSkill().equals("Braking"))
             {
                 RNG rng = new RNG(1, 8);
                 drivers.get(i).useSpecialSkill(rng);
             } 
             else {             
                    int third_lap = number_of_laps%3;
                    if(third_lap == 0)
                    {
                       RNG rng = new RNG(10, 20);
                       drivers.get(i).useSpecialSkill(rng);
                    }
             }
         }
     }
     
    public void checkMechanicalProblem() {
              
                RNG rng = new RNG(0, 100);
                for(int i=0; i<drivers.size(); i++)
                {
                        int previous_time = drivers.get(i).getAccumulatedTime();
                        int random_num = rng.getRandomValue();
                        if(random_num == UNRECOVERABLE_MECHANICAL_FAULT)  //1% of chance
                        {
                            drivers.get(i).setEligibleToRace(false);
                            System.out.println("Ispao je vozac " + drivers.get(i).getName());
                        }
                        else
                        {
                            random_num = rng.getRandomValue();
                            if(random_num < MAJOR_MECHANICAL_FAULT)  //3% of chance
                            {
                                previous_time += 120;
                                System.out.println("Vozac " + drivers.get(i).getName() + " je imao tezi mehanicki kvar");
                            }
                            else
                            {
                                random_num = rng.getRandomValue();
                                if(random_num < MINOR_MECHANICAL_FAULT)  //5% of chance
                                {
                                    System.out.println("Vozac " + drivers.get(i).getName() + " je imao laksi mehanicki kvar.");
                                    previous_time += 20;
                                }
                            }
                            drivers.get(i).setAccumulatedTime(previous_time);
                        }
                }   
    }
    
    public void checkRain(double chanceOfRain)
    {
            RNG rng = new RNG(0, 100);  
            int rand_int = rng.getRandomValue();
            int possibility = (int) chanceOfRain * 100;
            if(rand_int < possibility)
            {
                System.out.println(" ");
                System.out.println("KISA SE DESILA, ZATO : ");
                for(int i=0; i<drivers.size(); i++)
                {
                    if(drivers.get(i).isPneumatic()== false)
                    {
                        System.out.println("Vozac " + drivers.get(i).getName() + " nije imao suve pneumatike pa je vrijeme bilo" + drivers.get(i).getAccumulatedTime());
                        drivers.get(i).setAccumulatedTime(5 + drivers.get(i).getAccumulatedTime());
                        
                        System.out.println("a sada je " + drivers.get(i).getAccumulatedTime());
                    }
                }
                System.out.println("");
            }
    }
    
    
    public void printLeader(int lap) 
    {
           int min = BIG_VALUE;
           int driver_winner = 0;
           for(int i=0; i<drivers.size(); i++)
           {
               if(drivers.get(i).getAccumulatedTime() < min)
               {
                   min = drivers.get(i).getAccumulatedTime();
                   driver_winner = i;
               }
           }
           System.out.println("Pobjednik " + lap + ". kruga je: " + drivers.get(driver_winner).getName() + " zavrsenim sa " + min);
           System.out.println("");
    }
    
    public void printWinnersAfterRace(String venueName)
    {
        //sorting according to time
        Collections.sort(drivers, new SortDriversTime());
        
        for(int i=0; i<drivers.size(); i++)
        {
            drivers.get(i).setRanking(i+1);
        }
        System.out.println("Vozac na mjestu 1. nakon trke " + venueName + " je:  " + drivers.get(0).getName() + " sa vremenom:" + drivers.get(0).getAccumulatedTime());
        drivers.get(0).setAccumulatedPoints(8 + drivers.get(0).getAccumulatedPoints());
       
        int added_points = 5;
        for(int i = 1; i < 4; i++)
        { 
            System.out.println("Vozac na mjestu " +  drivers.get(i).getRanking() + ". nakon trke " + venueName + " je: " + drivers.get(i).getName() + "sa vremenom:" + drivers.get(i).getAccumulatedTime());
            drivers.get(i).setAccumulatedPoints(added_points + drivers.get(i).getAccumulatedPoints());
            added_points -= 2;
        } 
        
        System.out.println("Na kraju trke, tabela je: ");
        for(int i=0; i<drivers.size(); i++)
        { 
            System.out.println(i + ". " + drivers.get(i).getName() + ", sa poena:" + drivers.get(i).getAccumulatedPoints() + ",  rangom:" + drivers.get(i).getRanking());
        }
        System.out.println(" ");
    }
   
    void printChampion(int numOfRaces) 
    { 
        //sorting according to points
        Collections.sort(drivers, new SortDriversPoints(-1));
        System.out.println("Poslednja tabela");
        for(int i=1; i<=drivers.size(); i++)
        { 
            System.out.println( i + ". " + drivers.get(i-1).getName() + ",  sa poena:" + drivers.get(i-1).getAccumulatedPoints());
        }
        System.out.println("Pobjednik sampionata nakon odvezenih " + numOfRaces + " staza je: " + drivers.get(0).getName()  + " sa osvojenih " + drivers.get(0).getAccumulatedPoints()+ " poena. Cestitamo!");    
    }
    
    
    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public int getMINOR_MECHANICAL_FAULT() {
        return MINOR_MECHANICAL_FAULT;
    }

    public int getMAJOR_MECHANICAL_FAULT() {
        return MAJOR_MECHANICAL_FAULT;
    }

    public int getUNRECOVERABLE_MECHANICAL_FAULT() {
        return UNRECOVERABLE_MECHANICAL_FAULT;
    }
    
}
