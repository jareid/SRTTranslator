package com.jreid.srttranslator.srt;

import com.jreid.srttranslator.utils.LanguageCodes;
import com.jreid.srttranslator.utils.ResourceUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSRTTranslate {
    @Test
    public void translatesSubtitleEnToEs() {
        Subtitle testSubtitle = SRTParser.getSubtitlesFromFile("files/sub.srt", false, false);
        Subtitle translatedTestSubtitle = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitle,
                                                                   LanguageCodes.ENGLISH, LanguageCodes.SPANISH);
        Subtitle parsedResultSubtitle = SRTParser.getSubtitlesFromFile("files/translated_sub.srt", false, false);

        assertThat( translatedTestSubtitle, is( equalTo( parsedResultSubtitle) ) );
    }
}
