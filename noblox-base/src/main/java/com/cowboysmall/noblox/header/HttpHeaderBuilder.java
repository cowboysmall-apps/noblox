package com.cowboysmall.noblox.header;

import com.cowboysmall.noblox.util.DateFormatter;
import com.cowboysmall.noblox.util.HttpDateFormatter;

import java.util.Calendar;
import java.util.Map;

import static java.lang.String.format;
import static java.lang.String.join;

public class HttpHeaderBuilder implements HeaderBuilder {

    public static final String STATUS_OK = "200 OK";
    public static final String STATUS_CREATED = "201 Created";
    public static final String STATUS_ACCEPTED = "202 Accepted";
    public static final String STATUS_NO_CONTENT = "204 No Content";

    public static final String STATUS_MOVED_PERMANENTLY = "301 Moved Permanently";
    public static final String STATUS_FOUND = "302 Found";

    public static final String STATUS_BAD_REQUEST = "400 Bad Request";
    public static final String STATUS_FORBIDDEN = "403 Forbidden";
    public static final String STATUS_NOT_FOUND = "404 Not Found";

    public static final String STATUS_INTERNAL_SERVER_ERROR = "500 Internal Server Error";
    public static final String STATUS_BAD_GATEWAY = "502 Bad Gateway";
    public static final String STATUS_SERVICE_UNAVAILABLE = "503 Service Unavailable";


    //_________________________________________________________________________

    public static final String CONTENT_TYPE_TXT_PLAIN = "text/plain";
    public static final String CONTENT_TYPE_TXT_HTML = "text/html";
    public static final String CONTENT_TYPE_TXT_XML = "text/xml";

    public static final String CONTENT_TYPE_APP_JSON = "application/json";
    public static final String CONTENT_TYPE_APP_JS = "application/javascript";
    public static final String CONTENT_TYPE_APP_XML = "application/xml";


    //_________________________________________________________________________

    public static final String CONNECTION_KEEP_ALIVE = "keep-alive";
    public static final String CONNECTION_CLOSE = "close";


    //_________________________________________________________________________

    public static final String STATUS = "STATUS";
    public static final String CONTENT_LENGTH = "CONTENT_LENGTH";
    public static final String CONTENT_TYPE = "CONTENT_TYPE";
    public static final String CONNECTION = "CONNECTION";


    //_________________________________________________________________________

    private static final String RESPONSE_STATUS = "HTTP/1.1 %s\r\n";
    private static final String RESPONSE_DATE = "Date: %s\r\n";
    private static final String RESPONSE_CONTENT_LENGTH = "Content-Length: %s\r\n";
    private static final String RESPONSE_CONTENT_TYPE = "Content-Type: %s\r\n";
    private static final String RESPONSE_CONNECTION = "Connection: %s\r\n";


    //_________________________________________________________________________

    private DateFormatter dateFormatter;


    //_________________________________________________________________________

    public HttpHeaderBuilder(DateFormatter dateFormatter) {

        this.dateFormatter = dateFormatter;
    }

    public HttpHeaderBuilder() {

        this(new HttpDateFormatter());
    }


    //_________________________________________________________________________

    public DateFormatter getDateFormatter() {

        return dateFormatter;
    }

    public void setDateFormatter(DateFormatter dateFormatter) {

        this.dateFormatter = dateFormatter;
    }


    //_________________________________________________________________________

    @Override
    public String build(Map<String, String> values) {

        return join("",
                format(RESPONSE_STATUS, values.getOrDefault(STATUS, STATUS_OK)),
                format(RESPONSE_DATE, dateFormatter.format(Calendar.getInstance().getTime())),
                format(RESPONSE_CONTENT_LENGTH, values.getOrDefault(CONTENT_LENGTH, "0")),
                format(RESPONSE_CONTENT_TYPE, values.getOrDefault(CONTENT_TYPE, CONTENT_TYPE_TXT_PLAIN)),
                format(RESPONSE_CONNECTION, values.getOrDefault(CONNECTION, CONNECTION_CLOSE))
        );
    }
}
