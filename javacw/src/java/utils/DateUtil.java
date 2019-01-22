/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HaShaN
 */
public class DateUtil {
    private static final String EMPTY_STRING="";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    
    public static Date parse(String dateStr) throws ParseException{
        if (dateStr != null && !dateStr.isEmpty()) {
            return sdf.parse(dateStr);
        }else{
            return null;
        }
    }
    
    public static String format(Date date){
        if (date != null) {
            return sdf.format(date);
        }else{
            return EMPTY_STRING;
        }
        
    }
}
