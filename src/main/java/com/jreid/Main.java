package com.jreid;

import com.jreid.srttranslator.srt.SRTParser;
import com.jreid.srttranslator.srt.SRTTranslator;
import com.jreid.srttranslator.srt.Subtitle;
import com.jreid.srttranslator.utils.LanguageCodes;
import com.jreid.srttranslator.utils.ResourceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Application started");
        String fileName = "files/sub.srt";
        String outputName = fileName.substring(0,fileName.length() - 4) + "_" + LanguageCodes.SPANISH + ".srt";

        Subtitle testSubtitle = SRTParser.getSubtitlesFromFile(fileName, false, false);
        Subtitle translatedTestSubtitle = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitle,
                                                                   LanguageCodes.ENGLISH, LanguageCodes.SPANISH);
        Subtitle.toFile(outputName, translatedTestSubtitle);
    }
}

