package com.cowboysmall.noblox.io;

import com.cowboysmall.noblox.Channel;


public interface Reader {

    byte[] readFrom(Channel channel);
}
