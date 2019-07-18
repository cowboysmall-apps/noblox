package com.cowboysmall.noblox.header;

import java.util.Map;

public interface HeaderBuilder {

    String build(Map<String, String> values);
}
