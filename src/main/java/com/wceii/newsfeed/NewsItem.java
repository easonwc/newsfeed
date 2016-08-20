package com.wceii.newsfeed;

import java.util.Objects;

/**
 *
 * @author easonwc
 */
public class NewsItem {

    private String newsID;
    private String text;
    private long publicationDate;

    public NewsItem() {

    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String id) {
        this.newsID = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String newsText) {
        this.text = newsText;
    }

    public long getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(long date) {
        this.publicationDate = date;
    }

    public void setPublicationDate(java.util.Date date) {
        this.publicationDate = date.getTime();
    }

    public void setPublicationDate(java.sql.Date date) {
        this.publicationDate = date.getTime();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.newsID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
}
