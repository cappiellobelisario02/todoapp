package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoStringParser {

    private static final int DEFAULT_VALUE = -1;

    public static int stringToInt(String string) {
        try{
            return Integer.parseInt(string);
        }catch(NumberFormatException e){
            return DEFAULT_VALUE;
        }
    }

    public static LocalDate formatDateFromString(String string) {
        if(string.isEmpty() || string.equals("null")){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try{
            LocalDate dateTime = LocalDate.parse(string, formatter);
            System.out.println("Date successfully formatted in: " + dateTime);
            return dateTime;
        }catch (DateTimeParseException e){
            System.err.println("Couldn't parse date: " + string + ", error:" + e.getMessage());
        }
        return null;
    }

}
