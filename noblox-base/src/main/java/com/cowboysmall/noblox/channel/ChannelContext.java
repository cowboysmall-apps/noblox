package com.cowboysmall.noblox.channel;

import com.cowboysmall.noblox.dispatcher.Channel;
import com.cowboysmall.noblox.memory.InputBuffer;
import com.cowboysmall.noblox.memory.OutputBuffer;

import java.io.IOException;

public class ChannelContext implements ChannelWriter, ChannelBuffer {

    private OutputBuffer outputBuffer;
    private InputBuffer inputBuffer;
    private byte[] bytesRead;


    //_________________________________________________________________________

    public ChannelContext(InputBuffer inputBuffer, OutputBuffer outputBuffer) {

        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }


    //_________________________________________________________________________

    @Override
    public void readFrom(Channel channel) throws IOException {

        bytesRead = inputBuffer.readFrom(channel);
    }

    @Override
    public void writeTo(Channel channel) throws IOException {

        channel.write(outputBuffer);
    }

    @Override
    public byte[] bytesRead() {

        return bytesRead;
    }


    //_________________________________________________________________________

    @Override
    public void write(byte[] bytes) {

        outputBuffer.append(bytes);
    }

    @Override
    public void write(String string) {

        outputBuffer.append(string);
    }
}
