package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.header.HeaderBuilder;
import com.cowboysmall.noblox.header.http.HttpHeaderBuilder;
import com.cowboysmall.noblox.reactor.request.RequestContext;
import com.cowboysmall.noblox.reactor.request.RequestHandler;

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
    public void handleRequest(RequestContext requestContext) {

        byte[] bytesRead = requestContext.getInput().getBytes();

        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaderBuilder.CONTENT_LENGTH, Integer.toString(bytesRead.length));

        requestContext.getOutput().append(
                format(
                        HTTP_RESPONSE,
                        headerBuilder.build(map),
                        new String(bytesRead)
                ).getBytes()
        );
    }
}
