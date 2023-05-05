package com.jreid.srttranslator.srt;

import com.jreid.srttranslator.entities.SubtitleContents;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSRTParser {

    @Test
    public void testParsingSubtitle() {
        SubtitleContents subtitleContents = new SubtitleContents();
        assertThat(subtitleContents, is(equalTo(subtitleContents)));
    }
}
