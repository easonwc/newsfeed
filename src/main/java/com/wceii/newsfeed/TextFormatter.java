package com.wceii.newsfeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author easonwc
 */
public class TextFormatter {

    /**
     * Singleton instance.
     */
    private static TextFormatter instance;
    /**
     *
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     *
     */
    private static final String FRONT_END_DATE_FORMAT
            = "EEE MMM dd yyyy HH:mm:ss";

    /**
     *
     */
    private TextFormatter() {

    }

    /**
     *
     * @return
     */
    public static synchronized TextFormatter getInstance() {
        if (instance == null) {
            instance = new TextFormatter();
        }

        return instance;
    }

    /**
     *
     * @param date
     * @return
     */
    public String formatLongIntoDateString(final long date) {
        java.util.Date tmpDate = new java.util.Date(date);
        return this.formatDate(tmpDate);
    }

    /**
     *
     * @param date
     * @return
     */
    private synchronized String formatDate(final java.util.Date date) {
        final SimpleDateFormat DATE_FORMATTER
                = new SimpleDateFormat(DATE_FORMAT);
        final String timeString = DATE_FORMATTER.format(date);
        return timeString;
    }

    /**
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public synchronized java.util.Date formatStringIntoDate(
            final String dateString) throws ParseException {
        SimpleDateFormat formatter
                = new SimpleDateFormat(FRONT_END_DATE_FORMAT);
        java.util.Date date = formatter.parse(dateString);
        return date;
    }
}
