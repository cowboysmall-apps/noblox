package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.channel.ChannelContext;
import com.cowboysmall.noblox.dispatcher.Channel;
import com.cowboysmall.noblox.dispatcher.Key;

public class WriteHandler implements StateHandler {

    private Key key;
    private ChannelContext channelContext;


    //_________________________________________________________________________

    public WriteHandler(Key key, ChannelContext channelContext) {

        this.key = key;
        this.channelContext = channelContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            key.interested(0);
            Channel channel = key.getChannel();

            channelContext.writeTo(channel);

            channel.close();
            key.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
