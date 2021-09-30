package com.cowboysmall.noblox.buffer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test BasicBuffer...")
public class BasicBufferTest {

    @Test
    @DisplayName("Test getBytes method...")
    public void testBasicBuffer_GetBytes() {

        Buffer buffer = new BasicBuffer();

        buffer.append("Testes! ".getBytes());
        buffer.append("1, 2, 3?".getBytes());

        assertThat(new String(buffer.getBytes()), is(equalTo("Testes! 1, 2, 3?")));
    }

    @Test
    @DisplayName("Test getLength method...")
    public void testBasicBuffer_GetLength() {

        Buffer buffer = new BasicBuffer();

        buffer.append("Testes! ".getBytes());
        assertThat(buffer.getLength(), is(equalTo(8)));

        buffer.append("1, 2, 3?".getBytes());
        assertThat(buffer.getLength(), is(equalTo(16)));
    }

    @Test
    @DisplayName("Test null passed to append raises exception...")
    public void testBasicBuffer_Append_Null() {

        Buffer buffer = new BasicBuffer();

        assertThrows(
                IllegalArgumentException.class,
                () -> buffer.append(null)
        );
    }
}
