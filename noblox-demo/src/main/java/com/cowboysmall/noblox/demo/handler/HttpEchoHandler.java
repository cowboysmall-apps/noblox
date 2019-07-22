package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.Context;
import com.cowboysmall.noblox.header.HeaderBuilder;
import com.cowboysmall.noblox.header.http.HttpHeaderBuilder;
import com.cowboysmall.noblox.RequestHandler;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class HttpEchoHandler implements RequestHandler {

    private static final String HTTP_RESPONSE = "%s\r\n%s";

    private HeaderBuilder headerBuilder;


    //_________________________________________________________________________

    public HttpEchoHandler(HeaderBuilder headerBuilder) {

        this.headerBuilder = headerBuilder;
    }

    public HttpEchoHandler() {

        this(new HttpHeaderBuilder());
    }


    //_________________________________________________________________________

    @Override
    public void handleRequest(Context context) {

        byte[] bytesRead = context.getBytesRead();

        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaderBuilder.CONTENT_LENGTH, Integer.toString(bytesRead.length));

        context.getOutputBuffer().append(
                format(
                        HTTP_RESPONSE,
                        headerBuilder.build(map),
                        new String(bytesRead)
                )
        );
    }
}
