package com.jreid.srt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Subtitle {
	private final static Logger logger = LogManager.getLogger(Subtitle.class);

	private final static String SCAPE_TIME_TO_TIME = " --> ";

	private int id;
	private String startTime;
	private String endTime;
	private String text;
	private Subtitle nextSubtitle;

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

	public int getId() {
		return id;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getText() {
		return text;
	}

	public Subtitle getNextSubtitle() {
		return nextSubtitle;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setNextSubtitle(Subtitle nextSubtitle) {
		this.nextSubtitle = nextSubtitle;
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getString());
		Subtitle nextSubtitle = this.nextSubtitle;
		while( nextSubtitle != null ) {
			stringBuilder.append(nextSubtitle.getString())
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