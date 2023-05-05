package com.jreid.srttranslator.srt;

import com.jreid.srttranslator.entities.SubtitleContents;
import com.jreid.srttranslator.utils.LanguageCodes;
import com.jreid.srttranslator.utils.ResourceUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSRTTranslate {
    @Test
    public void translatesSubtitleEnToEs() {
        SubtitleContents testSubtitleContents = SRTParser.getSubtitlesFromFile("files/sub.srt", false, false);
        SubtitleContents translatedTestSubtitleContents = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitleContents,
                                                                   LanguageCodes.ENGLISH, LanguageCodes.SPANISH);
        SubtitleContents parsedResultSubtitleContents = SRTParser.getSubtitlesFromFile("files/translated_sub.srt", false, false);

        assertThat(translatedTestSubtitleContents, is( equalTo(parsedResultSubtitleContents) ) );
    }
}
