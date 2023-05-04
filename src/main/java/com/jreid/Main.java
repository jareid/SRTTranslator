package com.jreid;

import com.jreid.srt.SRTParser;
import com.jreid.srt.SRTTranslator;
import com.jreid.srt.Subtitle;
import com.jreid.utils.LanguageCodes;
import com.jreid.utils.ResourceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Application started");
        if (args.length == 3) {
            String fileName = "files/sub.srt";
            String outputName = fileName.substring(0,fileName.length() - 4) + "_" + LanguageCodes.SPANISH + ".srt";

            Subtitle testSubtitle = SRTParser.getSubtitlesFromFile(fileName, false, false);
            Subtitle translatedTestSubtitle = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitle,
                                                                       LanguageCodes.ENGLISH, LanguageCodes.SPANISH);
            Subtitle.toFile(outputName, translatedTestSubtitle);
        } else {
            LOGGER.error("Missing command line arguments, should be \"text languageFrom languageTo\"");
        }
    }
}

