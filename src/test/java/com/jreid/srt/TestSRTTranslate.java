package com.jreid.srt;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSRTTranslate {
    private static final String SPANISH_HELLO = "hola";
    private static final String ENGLISH_HELLO = "hello";

    @Test
    public void translatesEnToEs() {

        assertThat(ENGLISH_HELLO, is(equalTo("hello")));
    }

    @Test
    public void translatesSubtitleEsToEn() {
        assertThat(SPANISH_HELLO, is(equalTo("hola")));
    }
}
