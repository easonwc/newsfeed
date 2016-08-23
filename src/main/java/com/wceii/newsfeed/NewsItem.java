package com.wceii.newsfeed;

import java.util.Objects;

/**
 *
 * @author easonwc
 */
public class NewsItem implements Comparable<NewsItem> {

    private String newsID;
    private String text;
    private long publicationDate;

    public NewsItem() {

    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(final String id) {
        this.newsID = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String newsText) {
        this.text = newsText;
    }

    public long getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(final long date) {
        this.publicationDate = date;
    }

    public void setPublicationDate(final java.util.Date date) {
        this.publicationDate = date.getTime();
    }

    public void setPublicationDate(final java.sql.Date date) {
        this.publicationDate = date.getTime();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.newsID);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewsItem other = (NewsItem) obj;
        if (!Objects.equals(this.newsID, other.newsID)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final NewsItem o) {
        return Long.compare(this.publicationDate, o.publicationDate);
    }
}
