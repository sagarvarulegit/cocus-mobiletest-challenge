package com.example.cocusmobiletest.utils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class Temp {
    public static void main(String[] args) throws ParseException {
        // "Tue, 02 Jan 2018 18:07:59 IST"
        String dt = "2018-07-22T14:47:49.466Z";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dt);
        System.out.println(zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));

        // String dateString = "2018-07-22T14:47:49.466Z";
        // String pattern = "yyyy-MM-dd'T'HH:mm:ss.SZ";
        // SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // Date date = sdf.parse(dateString);
        // System.out.println(date); // Mon Mar 01 00:00:00 BOT 2010
    }
}
