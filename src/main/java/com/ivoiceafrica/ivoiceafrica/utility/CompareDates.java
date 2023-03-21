package com.ivoiceafrica.ivoiceafrica.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareDates {

	
	 public static String compareDates(String d1,String d2)
	    {
		 
		 String comparedOutput = "";
	        try{
	            // If you already have date objects then skip 1

	            //1
	            // Create 2 dates starts
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date date1 = (Date) sdf.parse(d1);
	            Date date2 = (Date) sdf.parse(d2);

	            System.out.println("Date1"+sdf.format(date1));
	            System.out.println("Date2"+sdf.format(date2));System.out.println();

	            // Create 2 dates ends
	            //1

	            // Date object is having 3 methods namely after,before and equals for comparing
	            // after() will return true if and only if date1 is after date 2
	            if(date1.after(date2)){
	                System.out.println("Date1 is after Date2");
	                comparedOutput = "after";
	            }
	            // before() will return true if and only if date1 is before date2
	            if(date1.before(date2)){
	                System.out.println("Date1 is before Date2");
	                comparedOutput = "before";
	            }

	            //equals() returns true if both the dates are equal
	            if(date1.equals(date2)){
	                System.out.println("Date1 is equal Date2");
	                comparedOutput = "equal";
	            }

	            System.out.println();
	        }
	        catch(ParseException ex){
	            ex.printStackTrace();
	            
	            System.out.println("===>>> Exception: "+ex.getMessage());
	        } catch(Exception ex) {
	        	 ex.printStackTrace();
	        	 
	        	 System.out.println("===>>> Exception: "+ex.getMessage());
	        }
	        
	        return comparedOutput;
	    }

	    public static String compareDates(Date date1,Date date2)
	    {
	    	
	    	String comparedOutput = "";
	    	
	        if(date1.after(date2)){
	            System.out.println("Date1 is after Date2");
	            comparedOutput = "after";
	        }

	        if(date1.before(date2)){
	            System.out.println("Date1 is before Date2");
	            comparedOutput = "before";
	        }

	        if(date1.equals(date2)){
	            System.out.println("Date1 is equal Date2");
	            comparedOutput = "equal";
	        }

	        System.out.println();
	        
	        return  comparedOutput;
	    }
}
