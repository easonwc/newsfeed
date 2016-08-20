package com.wceii.newsfeed;

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
    public String formatLongIntoDateString(long date) {
        java.util.Date tmpDate = new java.util.Date(date);
        return this.formatDate(tmpDate);
    }
    
    /**
     *
     * @param d
     * @return
     */
    private synchronized String formatDate(java.util.Date d) {
        final SimpleDateFormat DATE_FORMATTER
                = new SimpleDateFormat(DATE_FORMAT);
        final String timeString = DATE_FORMATTER.format(d);
        return timeString;
    }
}
