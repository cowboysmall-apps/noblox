package com.cowboysmall.noblox.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HttpDateFormatter implements DateFormatter {

    private static final String HTTP_DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    private SimpleDateFormat dateFormatter;


    //_________________________________________________________________________

    public HttpDateFormatter(Locale locale, TimeZone timeZone) {

        dateFormatter = new SimpleDateFormat(HTTP_DATE_PATTERN, locale);
        dateFormatter.setTimeZone(timeZone);
    }

    public HttpDateFormatter() {

        this(Locale.getDefault(), TimeZone.getTimeZone("GMT"));
    }


    //_________________________________________________________________________

    @Override
    public String format(Date date) {

        return dateFormatter.format(date);
    }

    @Override
    public Date parse(String source) throws ParseException {

        return dateFormatter.parse(source);
    }
}
