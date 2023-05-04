package com.jreid.srt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SRTParser {
	private final static Logger LOGGER = LogManager.getLogger(SRTParser.class);

	private static final Pattern PATTERN_TIME = Pattern.compile("([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}).*([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3})");
	private static final Pattern PATTERN_NUMBERS = Pattern.compile("(\\d+)");
	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private static final String REGEX_REMOVE_TAGS = "<[^>]*>";

	private static final int PATTERN_TIME_REGEX_GROUP_START_TIME = 1;
	private static final int PATTERN_TIME_REGEX_GROUP_END_TIME = 2;
	/**
	 *
	 * This method is responsible for parsing a STR file.
	 *
	 * @param path the subtitle to read
	 * @return a Subtitle object
	 */
	public static Subtitle getSubtitlesFromFile(String path, Boolean removeTags, Boolean removeNewlines) {
		Subtitle subtitle = null;
		StringBuilder srt;

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), DEFAULT_CHARSET))) {
			subtitle = new Subtitle();
			srt = new StringBuilder();

			while (bufferedReader.ready()) {
				String line = bufferedReader.readLine();

				Matcher matcher = PATTERN_NUMBERS.matcher(line);

				if (matcher.find()) {
					subtitle.setId(Integer.parseInt(matcher.group(1))); // index
					line = bufferedReader.readLine();
				}

				matcher = PATTERN_TIME.matcher(line);

				if (matcher.find()) {
					subtitle.setStartTime( matcher.group( PATTERN_TIME_REGEX_GROUP_START_TIME) ); // start time
					subtitle.setEndTime( matcher.group( PATTERN_TIME_REGEX_GROUP_END_TIME ) ); // end time
				}

				String aux;
				while ((aux = bufferedReader.readLine()) != null && !aux.isEmpty()) {
					srt.append(aux);
					if (!line.endsWith(" ")) {// for any new lines '\n' removed from BufferedReader
						srt.append("\n");
					}
				}

				// Remove new lines if requested
				if (removeNewlines) {
					srt.delete(srt.length()-1, srt.length()); // remove '\n' or space from end string
				}

				line = srt.toString();

				// Remove tags if requested
				if (removeTags && !line.isEmpty()) {
					line = line.replaceAll(REGEX_REMOVE_TAGS, ""); // clear all tags
				}

				srt.setLength(0); // Clear buffer so we start processing the next subtitle in the file

				subtitle.setText(line);

				// Set our next node in the Subtitle object
				subtitle.setNextSubtitle(new Subtitle());
				subtitle = subtitle.getNextSubtitle();
			}
		} catch (Exception exception) {
			LOGGER.fatal("error parsing srt file", exception);
		}
		return subtitle;
	}
}
