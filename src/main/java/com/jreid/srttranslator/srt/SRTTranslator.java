package com.jreid.srttranslator.srt;

import com.jreid.srttranslator.entities.SubtitleContents;
import com.jreid.srttranslator.utils.GoogleTranslateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SRTTranslator {
    private final static Logger LOGGER = LogManager.getLogger(SRTParser.class);

    /**
     * Translate Subtitle from one language to another
     *
     * @param googleApiKey the Google Translate API key to use
     * @param toTranslate  the text to translate
     * @param fromLanguage the language to translate from
     * @param toLanguage   the language to translate
     *
     * @return A translated Subtitle object
     */
    public static SubtitleContents translate(String googleApiKey, SubtitleContents toTranslate, String fromLanguage, String toLanguage) {
        SubtitleContents nextSubtitleToTranslateContents = toTranslate;

        while (nextSubtitleToTranslateContents != null && nextSubtitleToTranslateContents.isNotNull()) {
            String text = nextSubtitleToTranslateContents.getText();
            String translatedText = GoogleTranslateUtil.translate(googleApiKey, text, fromLanguage, toLanguage);
            nextSubtitleToTranslateContents.setText(translatedText);
            if (toTranslate.getNextSubtitleContents() != null && nextSubtitleToTranslateContents.isNotNull()) {
                nextSubtitleToTranslateContents = nextSubtitleToTranslateContents.getNextSubtitleContents();
            } else {
                nextSubtitleToTranslateContents = null;
            }
        }

        return toTranslate;
    }
}