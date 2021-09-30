package com.cowboysmall.noblox.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Testing HttpDateFormatter...")
public class HttpDateFormatterTest {

    @Test
    @DisplayName("Test format method...")
    public void testHttpDateFormatter_Format() {

        DateFormatter dateFormatter = new HttpDateFormatter();

        assertThat(
                dateFormatter.format(
                        Date.from(
                                LocalDate.of(1970, Month.APRIL, 27)
                                        .atStartOfDay()
                                        .atZone(TimeZone.getTimeZone("GMT").toZoneId())
                                        .toInstant()
                        )
                ),
                is(equalTo("Mon, 27 Apr 1970 00:00:00 GMT"))
        );
    }

    @Test
    @DisplayName("Test parse method...")
    public void testHttpDateFormatter_Parse() throws Exception {

        DateFormatter dateFormatter = new HttpDateFormatter();

        LocalDate localDate =
                dateFormatter.parse("Mon, 27 Apr 1970 00:00:00 GMT")
                        .toInstant()
                        .atZone(TimeZone.getTimeZone("GMT").toZoneId())
                        .toLocalDate();

        assertThat(localDate.getYear(), is(equalTo(1970)));
        assertThat(localDate.getMonth(), is(equalTo(Month.APRIL)));
        assertThat(localDate.getDayOfMonth(), is(equalTo(27)));
    }
}
