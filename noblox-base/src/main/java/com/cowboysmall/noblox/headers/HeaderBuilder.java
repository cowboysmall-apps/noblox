package com.cowboysmall.noblox.headers;

import java.util.Map;

public interface HeaderBuilder {

    String build(Map<String, String> values);
}
