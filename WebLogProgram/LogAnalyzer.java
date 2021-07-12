
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     

     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>(); 
     }
        
     public void readFile(String filename) {
         // complete method
         records.clear();
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
           WebLogParser wlp = new WebLogParser();
           LogEntry le = wlp.parseEntry(line);
           //return le; 
           records.add(le);
            }  
         
     }
     
     public int countUniqueIPs(){
       ArrayList<String>uniqueIPs = new ArrayList<String>();
       
       for (LogEntry le : records){
         String IP = le.getIpAddress();
         if (!uniqueIPs.contains(IP)){
         uniqueIPs.add(IP);
        }
        }
        
       return uniqueIPs.size(); 
        
        }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
      for (LogEntry le : records){
        int status = le.getStatusCode();
        if(status > num){
          System.out.println(le);
        }
        
        }
    
    } 
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
      //format MMM DD
      ArrayList<String>uniqueIpDate = new ArrayList<String>();
      uniqueIpDate.clear();
      
      for (LogEntry le : records){
        
        Date date = le.getAccessTime();
        String d = date.toString();
        if (d.contains(someday)){
          String IP = le.getIpAddress();
          if(!uniqueIpDate.contains(IP)){
            uniqueIpDate.add(IP);
          }
         
        }
        
        }
      
      return uniqueIpDate; 
    
    }
    
    
    public int countUniqueIPsInRange(int low, int high){
      //int range = 0;
      ArrayList<String> IPStatus = new ArrayList<String>();
      IPStatus.clear();
      for (LogEntry le : records){
        int status = le.getStatusCode();
        
        if(status >= low && status <= high){  
         String IP = le.getIpAddress();
         if (!IPStatus.contains(IP)){
         IPStatus.add(IP);
        }
        
       }
        
        
        }
    
      return IPStatus.size();  
        
    }
    
    
    public HashMap<String,Integer>countVisitsPerIP(){
      //records stores Logentries from a file of web logs
      HashMap<String,Integer>ipVisits = new HashMap<String,Integer>();
      ipVisits.clear();
       
      for (LogEntry le : records){
        String IP = le.getIpAddress();
        if(!ipVisits.containsKey(IP)){
          ipVisits.put(IP,1);  
            
        }else{
         ipVisits.put(IP,ipVisits.get(IP)+1);
        
        }
        
        }
    
      return ipVisits;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer>hm){
      //HM parameter maps IP addy to frequency
      int largestSoFar =0;
      for (String s : hm.keySet()){
        int value = hm.get(s);
        if(value>largestSoFar){
          largestSoFar = value;
        }
        
        }
      
      return largestSoFar;
    }
    
    
    public ArrayList<String>iPsMostVisits(HashMap<String,Integer>hm){
     ArrayList<String> IPLargest = new ArrayList<String>();
     IPLargest.clear();
     int high = mostNumberVisitsByIP(hm);
     
     for (String s : hm.keySet()){
       int value = hm.get(s);
       
       if(value == high){
         IPLargest.add(s);
        }
        
        }
     
     return IPLargest;
    }
    
    public HashMap<String,ArrayList<String>>iPsForDays(){
      //uses records to map days from logentries 
      //to an arrayList of IPs that occured on that day
      /*
       * 110.76.104.12 Wed Sep 30 07:47:11 EDT 2015 GET //favicon.ico HTTP/1.1 200 3426
         152.3.135.44 Wed Sep 30 07:47:11 EDT 2015 GET /projects/rotate/ HTTP/1.1 302 495
         152.3.135.44 Wed Sep 30 07:47:11 EDT 2015 GET /projects/rotate/random_photo/ HTTP/1.1 200 1112
         152.3.135.44 Wed Sep 30 07:47:11 EDT 2015 GET /favicon.ico HTTP/1.1 304 179
         157.55.39.203 Wed Sep 30 07:47:13 EDT 2015 GET /courses/spring14/cps140/info.php HTTP/1.1 200 2913
         152.3.135.63 Wed Sep 30 07:47:13 EDT 2015 GET /projects/rotate4k/ HTTP/1.1 302 492
         152.3.135.63 Wed Sep 30 07:47:13 EDT 2015 GET /projects/rotate4k/random_photo/ HTTP/1.1 200 1055

       */
      HashMap<String,ArrayList<String>>hm = new HashMap<String,ArrayList<String>>();
      hm.clear();
      for (LogEntry le : records){
        Date dateNight = le.getAccessTime();
        String dee = dateNight.toString();
        String date = dee.substring(4,10);
        String IP = le.getIpAddress();
        
        
        if (!hm.containsKey(date)){
          ArrayList<String> iPad = new ArrayList<String>(); 
          iPad.clear();
          //IP = le.getIpAddress();
          //iPad
          iPad.add(IP);   
          hm.put(date,iPad);
          }
        else{
          ArrayList<String>AL = hm.get(date);
          
          AL.add(IP);
            
          
        }
          
        }
      return hm;
    }
    
    public String dayWithMostIPVisits (HashMap<String,ArrayList<String>>hashy){
      int largestSoFar = 0;
      String largestDay = " ";
      for (String s : hashy.keySet()){
        ArrayList<String> AL = hashy.get(s);
        int size = AL.size();
        
        if(size>largestSoFar){
          largestSoFar = size;
          largestDay = s;
        
        }
        
        
        }
    
      return largestDay;  
        
    }
    
    public ArrayList<String>IPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>>HM,String day){
      ArrayList<String> mostIPs = new ArrayList<String>();
      mostIPs.clear();
      //dayWithMostIPVisits(); omg
      for (String s : HM.keySet()){
        
        if(s.contains(day)){
          ArrayList aL = HM.get(s);//iP addies on that day
          HashMap<String,Integer>ipVisits = new HashMap<String,Integer>();
          ipVisits.clear();
         for (int k = 0; k< aL.size(); k++){
          String IP = aL.get(k).toString();
          //iP addies on that day and visits
          //ipVisits.clear();
           if(!ipVisits.containsKey(IP)){
            ipVisits.put(IP,1);  
            
           }else{
           ipVisits.put(IP,ipVisits.get(IP)+1); 
        
          }
        
         }
         
         mostIPs = iPsMostVisits(ipVisits);
        }
        
        }  
      return mostIPs;
    
    }
    
    
}
