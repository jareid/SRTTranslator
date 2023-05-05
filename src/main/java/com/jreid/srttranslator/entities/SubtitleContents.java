package com.jreid.srttranslator.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * A Subtitle class that represents the Subtitle objects with their times and text
 * Acts as a Linked List
 * TODO Add more linkedList methods
 */
@Data
@Entity
public class SubtitleContents {
	private final static Logger logger = LogManager.getLogger(SubtitleContents.class);

	private final static String SCAPE_TIME_TO_TIME = " --> ";

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	@NotNull
	private String startTime;

	@Column
	@NotNull
	private String endTime;

	@Column
	@NotNull
	private String text;

	@OneToOne
	private SubtitleContents previousSubtitleContents = null;

	@OneToOne
	private SubtitleContents nextSubtitleContents = null;

	/**
	 * Empty Constructor
	 */
	public SubtitleContents() {

	}

	/**
	 * Copy Constructor
	 * @param otherSubtitleContents The Subtitle object to copy
	 */
	public SubtitleContents(SubtitleContents otherSubtitleContents) {
		this.id = otherSubtitleContents.id;
		this.startTime = otherSubtitleContents.startTime;
		this.endTime = otherSubtitleContents.endTime;
		this.text = otherSubtitleContents.text;
		this.nextSubtitleContents = otherSubtitleContents.nextSubtitleContents;
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

	public boolean isNotNull() {
		return id != null && id != 0 && text != null;
	}

	/**
	 * Gets the Subtitle object at a particular index
	 * @param index index of subtitle object wanted
	 * @return a Subtilte object
	 */
	public SubtitleContents get(int index) {
		if (this.id == 0) return null;
		if (this.id == index) return this;
		SubtitleContents nextSubtitleContents = this.nextSubtitleContents;
		while (nextSubtitleContents.isNotNull()) {
			if ( nextSubtitleContents.getId() == index) return nextSubtitleContents;
			else nextSubtitleContents = nextSubtitleContents.getNextSubtitleContents();
		}
		return null;
	}

	public SubtitleContents getFirst() {
		return null;
	}

	public SubtitleContents getLast() {
		return null;
	}

	/**
	 * Returns the number of Subtitle objects
	 * @return the size of the Subtitle object
	 */
	public int size() {
		if (!this.isNotNull()) return 0;
		else {
			int size = 1;
			SubtitleContents nextSubtitleContents = this.nextSubtitleContents;
			while ( nextSubtitleContents.isNotNull() ) {
				if ( !nextSubtitleContents.isNotNull() ) return size;
				else nextSubtitleContents = nextSubtitleContents.getNextSubtitleContents();
			}
			return size;
		}
	}

	@Override
	public int hashCode() {
		if (id == 0) return 0;
		return id.hashCode() + text.hashCode() + startTime.hashCode() + endTime.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true; // self check
		if (object == null) return false; // null check
		if (getClass() != object.getClass()) return false; // type check and cast

		// object comparison
		SubtitleContents otherSubtitleContents = (SubtitleContents) object;
		return Objects.equals(id, otherSubtitleContents.id)
				&& Objects.equals(text, otherSubtitleContents.text)
				&& Objects.equals(startTime, otherSubtitleContents.startTime)
				&& Objects.equals(endTime, otherSubtitleContents.endTime)
				&& Objects.equals(nextSubtitleContents, otherSubtitleContents.nextSubtitleContents);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		// Write the current Subtitle
		stringBuilder.append(getString())
					 .append(System.lineSeparator())
					 .append(System.lineSeparator());

		// Process subsequent subtitles
		SubtitleContents nextSubtitleContents = this.nextSubtitleContents;
		while(nextSubtitleContents.isNotNull()) {
			stringBuilder.append(nextSubtitleContents.getString())
						 .append(System.lineSeparator())
						 .append(System.lineSeparator());
			nextSubtitleContents = nextSubtitleContents.nextSubtitleContents;
		}

		return stringBuilder.toString();
	}

	/**
	 * Outputs the Subtitle and all linked Subtitle objects to a file
	 *
	 * @param fileName The file to store the Subtitle in
	 * @param subtitleContents The Subtitle object to write to a file
	 *
	 * @return	a File object
	 */
	public static File toFile(String fileName, SubtitleContents subtitleContents) {
		File file = new File(fileName);

		try {
			FileWriter writer = new FileWriter(file);
			writer.write(subtitleContents.toString());
			writer.close();

			logger.info("Subtitle successfully wrote to the file:" + fileName);
		} catch (IOException ioException) {
			logger.fatal(ioException.getMessage());
		}

		return file;
	}
}