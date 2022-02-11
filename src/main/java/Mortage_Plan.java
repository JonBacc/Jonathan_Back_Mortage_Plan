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
import java.util.Scanner; // Import the Scanner class to read text files
import java.lang.Object; 
import java.util.ArrayList;//Used for ArrayList<>
import java.util.List;//Used for List<>

public class Mortage_Plan {
    public static int outerCounter = 0;
    public static void main(String[] args) {
        
    try {
      File myObj = new File("src/main/resources/prospects.txt");
      Scanner myReader = new Scanner(myObj, "UTF-8");
      
      //String header = myReader.nextLine();
      //System.out.println(header);
      myReader.nextLine();
      while (myReader.hasNextLine()) {
        
        String data = myReader.nextLine();
        //System.out.println(data);
        if(data.length()>1){
            outerCounter++;
            String[] prospects = data.split(",");
//            System.out.println(prospects[0]);
            String name = String.valueOf(prospects[0]);
            Float total_loan = Float.valueOf(prospects[1]);
            Float interest = Float.valueOf(prospects[2]);
            Float years = Float.valueOf(prospects[3]);
            Float months = years * 12;
            Float monthly_interest = interest / months;
            Float result = 1.0f;
            
            while (months != 0) {
                result = result * (1 + monthly_interest);
                // power will get reduced after
                // each multiplication
                months--;
            }
            
//            E = U[b(1 + b)^p]/[(1 + b)^p - 1]
//            E = Fixed monthly payment
//            b = Interest on a monthly basis
//            U = Total loan
//            p = Number of payment
            Float monthlypayment = (total_loan*monthly_interest*result)/(result-1);
            
            
            System.out.println("Prospect " + outerCounter + ": " 
                    +  name
                    + " wants to borrow " 
                    + total_loan
                    + " € for a period of "
                    + years
                    + " years and pay "
                    + monthlypayment
                    + " each month ");
        }
        
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
        
  }
}
