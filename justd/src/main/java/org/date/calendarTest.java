package org.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class calendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("现在是："+calendar.get(GregorianCalendar.YEAR)+"年"+
                        (calendar.get(GregorianCalendar.MONTH))+"月"+
                        calendar.get(GregorianCalendar.DAY_OF_MONTH)+"日"+
                        calendar.get(GregorianCalendar.HOUR)+"时"+
                        calendar.get(GregorianCalendar.MINUTE)+"分"+
                        calendar.get(GregorianCalendar.SECOND)+"秒"
        );
    }
}
