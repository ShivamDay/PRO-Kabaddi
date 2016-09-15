package com.prokabaddi.myscheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Shivam
 * 
 */

public class DateTimeSerializer extends JsonSerializer<Date> {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	@Override
	public void serialize(final Date aDate, final JsonGenerator aJsonGenerator,
			final SerializerProvider aSerializerProvider) throws IOException {
		final String dateString = formatDateTimetoString(aDate);
		aJsonGenerator.writeString(dateString);
	}

	public static String formatDateTimetoString(final Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * This method converts the dateTime to specified format.
	 * 
	 * @param date
	 *            the date
	 * @return String
	 */
	public static String formatDate(final Date date, String format) {
		String chkFormat = format;
		if (chkFormat == null) {
			chkFormat = YYYY_MM_DD;
		}
		final SimpleDateFormat dateFormat = new SimpleDateFormat(chkFormat);
		return dateFormat.format(date);
	}

}