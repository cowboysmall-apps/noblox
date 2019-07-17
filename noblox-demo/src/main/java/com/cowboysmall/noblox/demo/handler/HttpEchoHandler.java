package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.RequestHandler;
import com.cowboysmall.noblox.headers.HeaderBuilder;
import com.cowboysmall.noblox.headers.HttpHeaderBuilder;

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
    public void handleRequest(Channel channel, byte[] input) {

        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaderBuilder.CONTENT_LENGTH, Integer.toString(input.length));

        channel.write(
                format(
                        HTTP_RESPONSE,
                        headerBuilder.build(map),
                        new String(input)
                )
        );
    }
}
