package com.jreid;

import com.jreid.srt.SRTParser;
import com.jreid.srt.SRTTranslator;
import com.jreid.srt.Subtitle;
import com.jreid.utils.ResourceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Application started");
        if (args.length == 3) {
            Subtitle testSubtitle = SRTParser.getSubtitlesFromFile(args[0], false, false);
            String outputName = args[0].substring(0,args[0].length() - 4) + "_" + args[2] + ".srt";
            Subtitle translatedTestSubtitle = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitle, args[1], args[2]);
            Subtitle.toFile(outputName, translatedTestSubtitle);
        } else {
            LOGGER.error("Missing command line arguments, should be \"text languageFrom languageTo\"");
        }
    }
}

