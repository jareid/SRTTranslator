package com.jreid.srttranslator;

import com.jreid.srttranslator.srt.SRTParser;
import com.jreid.srttranslator.srt.SRTTranslator;
import com.jreid.srttranslator.entities.SubtitleContents;
import com.jreid.srttranslator.utils.LanguageCodes;
import com.jreid.srttranslator.utils.ResourceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SrttranslatorApplication {
	private final static Logger LOGGER = LogManager.getLogger(SrttranslatorApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Application started");
		/*String fileName = "files/sub.srt";
		String outputName = fileName.substring(0,fileName.length() - 4) + "_" + LanguageCodes.SPANISH + ".srt";

		SubtitleContents testSubtitleContents = SRTParser.getSubtitlesFromFile(fileName, false, false);
		SubtitleContents translatedTestSubtitleContents = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitleContents,
				LanguageCodes.ENGLISH, LanguageCodes.SPANISH);
		SubtitleContents.toFile(outputName, translatedTestSubtitleContents);*/


		SpringApplication.run(SrttranslatorApplication.class, args);
	}

}
