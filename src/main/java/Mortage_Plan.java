/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jonathan
 */

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; //Used for ArrayList<>
import java.util.Arrays;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mortage_Plan {

    public static void main(String[] args) {
     int rows=0;
     int outerCounter=0;
     ArrayList<ArrayList<String>> prospects = new ArrayList<ArrayList<String>>(); 
     
    try {
      File myObj = new File("src/main/resources/prospects.txt");
      Scanner myReader = new Scanner(myObj, "UTF-8");
      
       while (myReader.hasNextLine()) {
       String data = myReader.nextLine();
       
       if (data.length() > 1) {
            if (data.startsWith("\"")) {
                data = data.replace("\"","");
                data = data.replaceFirst("(?:,)+", " ");
            }
            
        
       
        ArrayList<String> line = new ArrayList<String>();     
        String[] items = data.split(",");

        for (int i = 0; i < items.length; i++) {
            line.add(items[i]);
        }
        
        prospects.add(line);
        rows++;
        Arrays.fill(items, null); // to clear out the 'items' array
       }
      }
       
       while(outerCounter<rows-1){
            outerCounter++;
            String name = String.valueOf(prospects.get(outerCounter).get(0));
            Float total_loan = Float.valueOf(prospects.get(outerCounter).get(1));
            Float interest = Float.valueOf(prospects.get(outerCounter).get(2));
            Float years = Float.valueOf(prospects.get(outerCounter).get(3));
            Float months = years * 12;
            Float monthly_interest = interest / months;
            Float result = 1.0f;
            
            while (months != 0) {
                result = result * (1 + monthly_interest);
                // power will get reduced after
                // each multiplication
                months--;
            }

            
            float monthlypayment = (total_loan*monthly_interest*result)/(result-1);
            DecimalFormat f = new DecimalFormat("##.00");
            DecimalFormat e = new DecimalFormat("##");
   
            System.out.println("Prospect " + outerCounter + ": " 
                    +  name
                    + " wants to borrow " 
                    + (f.format(total_loan))
                    + " € for a period of "
                    + (e.format(years))
                    + " years and pay "
                    + (f.format(monthlypayment))
                    + "€ each month ");
        
       }
       
      myReader.close();
      System.out.println("Press Enter key to continue...");
         try {
             System.in.read();
             System.exit(0);
         } catch (IOException ex) {
             Logger.getLogger(Mortage_Plan.class.getName()).log(Level.SEVERE, null, ex);
         }
      
      
    } 
    
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    
  }
}