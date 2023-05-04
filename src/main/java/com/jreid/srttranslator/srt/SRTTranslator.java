package com.jreid.srttranslator.srt;

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
    public static Subtitle translate(String googleApiKey, Subtitle toTranslate, String fromLanguage, String toLanguage) {
        Subtitle nextSubtitleToTranslate = toTranslate;

        while (nextSubtitleToTranslate != null && nextSubtitleToTranslate.isNotNull()) {
            String text = nextSubtitleToTranslate.getText();
            String translatedText = GoogleTranslateUtil.translate(googleApiKey, text, fromLanguage, toLanguage);
            nextSubtitleToTranslate.setText(translatedText);
            if (toTranslate.getNextSubtitle() != null && nextSubtitleToTranslate.isNotNull()) {
                nextSubtitleToTranslate = nextSubtitleToTranslate.getNextSubtitle();
            } else {
                nextSubtitleToTranslate = null;
            }
        }

        return toTranslate;
    }
}