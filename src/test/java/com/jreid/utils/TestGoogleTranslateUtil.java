package com.jreid.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGoogleTranslateUtil {
    private static final String SPANISH_HELLO = "hola";
    private static final String ENGLISH_HELLO = "hello";

    @Test
    public void translatesEnToEs() {
        String translateTest = GoogleTranslateUtil.translate( ResourceUtil.getGoogleApiKey(),
                                                              ENGLISH_HELLO,
                                                              LanguageCodes.ENGLISH,
                                                              LanguageCodes.SPANISH);
        assertThat(SPANISH_HELLO, is(equalTo(translateTest != null ? translateTest.toLowerCase() : "")));
    }

    @Test
    public void translatesEsToEn() {

        String translateTest = GoogleTranslateUtil.translate( ResourceUtil.getGoogleApiKey(),
                                                              SPANISH_HELLO,
                                                              LanguageCodes.SPANISH,
                                                              LanguageCodes.ENGLISH);
        assertThat(ENGLISH_HELLO, is(equalTo(translateTest != null ? translateTest.toLowerCase() : "")));
    }
}
