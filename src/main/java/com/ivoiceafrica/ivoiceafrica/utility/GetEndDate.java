package com.ivoiceafrica.ivoiceafrica.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetEndDate {

	
	public static Date calculateEndDate(Date startDate, int duration) {
        Calendar startCal = Calendar.getInstance();

        startCal.setTime(startDate);//from  w  ww  . j  a va2 s  .co m
        for (int i = 1; i < duration; i++) {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            while (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return startCal.getTime();
    }
	
	public static Date calculateNextSettleDate(Date date, int addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);/*from  w ww .  jav  a  2s  . c o  m*/
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int maxOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (dayOfMonth == maxOfMonth) {
            calendar.add(Calendar.MONTH, addMonth);
            maxOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, maxOfMonth);
        } else {
            calendar.add(Calendar.MONTH, addMonth);
        }
        return calendar.getTime();
    }

    public static Date add(Date when, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        calendar.add(field, amount);
        return calendar.getTime();
    }
	
	
	public static void main(String[] args) {
		
		String sd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date startDate;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
			Date endDate = calculateEndDate(startDate, 2);
			
			System.out.println("EndDate: "+endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
