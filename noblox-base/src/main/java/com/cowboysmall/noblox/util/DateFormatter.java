package com.cowboysmall.noblox.util;

import java.text.ParseException;
import java.util.Date;

public interface DateFormatter {

    String format(Date date);

    Date parse(String source) throws ParseException;
}
