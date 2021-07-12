
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer lA = new LogAnalyzer();
        lA.readFile("weblog2_log");
        lA.printAll();
        
    } 
    
    public void testUniqueIP(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        int totaluniqueIPs = LA.countUniqueIPs();
        
        System.out.println("Total unique IPs are: "+totaluniqueIPs);
    
    
    } 
    
    public void testHigherThanNum(){
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog1_log");
      System.out.println("This many status codes are greater than 400:");
      la.printAllHigherThanNum(400);
      
    
      
    
    }

    
    public void uniqueIPVisitsOnDayTest(){
     LogAnalyzer logg = new LogAnalyzer();
     logg.readFile("weblog2_log");
     ArrayList<String>ipDates = logg.uniqueIPVisitsOnDay("Sep 24");
     System.out.println("This many IPs that requested on Sep 24: "+ipDates.size());
     //logg.printAll();
    
    }
    
    public void statusIPTest(){
      LogAnalyzer logz = new LogAnalyzer();
      logz.readFile("weblog2_log");
      int range = logz.countUniqueIPsInRange(200,299);
      
      System.out.println("This many IPs have status codes w/i the range 200-299: "+range);
    
    
    }
    
    public void testHashMap(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log"); 
        HashMap mappy = LA.countVisitsPerIP();
        
        //System.out.println(mappy);
        ArrayList<String>large = LA.iPsMostVisits(mappy);
        
        System.out.println(large);
    }
    
    public void testIPForDays(){
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      HashMap<String,ArrayList<String>>happy = la.iPsForDays();
      ArrayList<String> ip = la.IPsWithMostVisitsOnDay(happy,"Sep 29");
      System.out.println(ip); 
      //System.out.println(
      
    
    }
    
    public void testIPWithMostVisitsOnDay(){
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog3-short_log");
      HashMap<String,ArrayList<String>>hashy = la.iPsForDays();
      
      ArrayList<String> abc = la.IPsWithMostVisitsOnDay(hashy,"Sep 30");
      System.out.println(abc);
    
    }
}
