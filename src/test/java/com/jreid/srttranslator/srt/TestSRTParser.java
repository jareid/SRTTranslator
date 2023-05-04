package com.jreid.srttranslator.srt;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSRTParser {

    @Test
    public void testParsingSubtitle() {
        Subtitle subtitle = new Subtitle();
        assertThat(subtitle, is(equalTo(subtitle)));
    }
}
