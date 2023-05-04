package com.jreid.srt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

@Data
public class Subtitle {
	private final static Logger logger = LogManager.getLogger(Subtitle.class);

	private final static String SCAPE_TIME_TO_TIME = " --> ";

	private int id;
	private String startTime;
	private String endTime;
	private String text;
	private Subtitle nextSubtitle = null;

	/**
	 * Empty Constructor
	 */
	public Subtitle() {

	}

	/**
	 * Copy Constructor
	 * @param otherSubtitle The Subtitle object to copy
	 */
	public Subtitle(Subtitle otherSubtitle) {
		this.id = otherSubtitle.id;
		this.startTime = otherSubtitle.startTime;
		this.endTime = otherSubtitle.endTime;
		this.text = otherSubtitle.text;
		this.nextSubtitle = otherSubtitle.nextSubtitle;
	}

	public String getString() {
		return id +
				System.lineSeparator() +
				startTime +
				SCAPE_TIME_TO_TIME +
				endTime +
				System.lineSeparator() +
				text;
	}

	public boolean isNull() {
		return id == 0 || text == null;
	}

	@Override
	public int hashCode() {
		if (id == 0) return 0;
		return id + text.hashCode() + startTime.hashCode() + endTime.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true; // self check
		if (object == null) return false; // null check
		if (getClass() != object.getClass()) return false; // type check and cast

		// object comparison
		Subtitle otherSubtitle = (Subtitle) object;
		return Objects.equals(id, otherSubtitle.id)
				&& Objects.equals(text, otherSubtitle.text)
				&& Objects.equals(startTime, otherSubtitle.startTime)
				&& Objects.equals(endTime, otherSubtitle.endTime)
				&& Objects.equals(nextSubtitle, otherSubtitle.nextSubtitle);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		// Write the current Subtitle
		stringBuilder.append(getString())
					 .append(System.lineSeparator())
					 .append(System.lineSeparator());

		// Process subsequent subtitles
		Subtitle nextSubtitle = this.nextSubtitle;
		while( !nextSubtitle.isNull() ) {
			stringBuilder.append(nextSubtitle.getString())
						 .append(System.lineSeparator())
						 .append(System.lineSeparator());
			nextSubtitle = nextSubtitle.nextSubtitle;
		}

		return stringBuilder.toString();
	}

	/**
	 * Outputs the Subtitle and all linked Subtitle objects to a file
	 *
	 * @param fileName The file to store the Subtitle in
	 * @param subtitle The Subtitle object to write to a file
	 *
	 * @return	a File object
	 */
	public static File toFile(String fileName, Subtitle subtitle) {
		File file = new File(fileName);

		try {
			FileWriter writer = new FileWriter(file);
			writer.write(subtitle.toString());
			writer.close();

			logger.info("Subtitle successfully wrote to the file:" + fileName);
		} catch (IOException ioException) {
			logger.fatal(ioException.getMessage());
		}

		return file;
	}
}