package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.channel.ChannelWriter;
import com.cowboysmall.noblox.request.RequestHandler;
import com.cowboysmall.noblox.header.HeaderBuilder;
import com.cowboysmall.noblox.header.HttpHeaderBuilder;

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
    public void handleRequest(ChannelWriter channelWriter, byte[] input) {

        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaderBuilder.CONTENT_LENGTH, Integer.toString(input.length));

        channelWriter.write(
                format(
                        HTTP_RESPONSE,
                        headerBuilder.build(map),
                        new String(input)
                )
        );
    }
}
