# SRTParser

SRTTranslator is everything that you need to translate a subtitle file

## Installation

### Option 1
As a lightweight version of the code, the _best way to make use of it is cloning the code and adding it to your project.

### Option 2
Include the following in your Maven project file:

```	
		<dependency>
			<groupId>com.jreid</groupId>
			<artifactId>srttranslator</artifactId>
			<version>0.0.1</version>
		</dependency>
```	

## Contributing_
Pull requests are welcome.

Please make sure to update tests as appropriate.

## What is this project for?

The purpose of this project is to give Java an easy, fast, performant and light weight SRT translator library.

With SRTTranslator you can easily translate a subtitle into any language from another language provided you know the language code to use.

## The usage of the project:

To start you will need a google API key, instructions on how to obtain one can be found here:
https://weglot.com/blog/google-translate-api-key/
This API Key should be stored in google.properties in the apiKey resource.

The below code takes a subtitle file named test.srt and translates it from English to Spanish and outputs the resulting SRT file

```	
String fileName = "test.srt";
String translateFrom = LanguageCodes.ENGLISH;
String translateTo = LanguageCodes.SPANISH;
Subtitle testSubtitle = SRTParser.getSubtitlesFromFile(fileName, false, false);
String outputName = fileName.substring(0,fileName - 4) + "_" + translateTo + ".srt";
Subtitle translatedTestSubtitle = SRTTranslator.translate( ResourceUtil.getGoogleApiKey(), testSubtitle, translateFrom, translateTo);
Subtitle.toFile(outputName, translatedTestSubtitle);
```

## Version (and Version History)

Version 0.0.1 - Project started
