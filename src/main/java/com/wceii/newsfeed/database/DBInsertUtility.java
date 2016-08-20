package com.wceii.newsfeed.database;

import com.wceii.newsfeed.NewsItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.naming.NamingException;

/**
 *
 * @author easonwc
 */
public class DBInsertUtility {
   
    /**
     *
     */
    private static DBInsertUtility instance;
    /**
     *
     */
    private static final String INSERT_NEWS_ITEM = "insert into NewsItem "
            + "(idNewsItem,newsText,datePublished) values (?,?,?)";

    /**
     * Empty constructor.
     */
    private DBInsertUtility() {

    }

    /**
     *
     * @return
     */
    public static synchronized DBInsertUtility getInstance() {
        if (instance == null) {
            instance = new DBInsertUtility();
        }

        return instance;
    }

    /**
     * 
     * @param item
     * @throws SQLException
     * @throws NamingException 
     */
    public void insertNewsItem(final NewsItem item) throws SQLException,
            NamingException {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {

            conn = DatabaseUtility.getInstance().getDataSource()
                    .getConnection();
            statement = conn.prepareStatement(INSERT_NEWS_ITEM);

            statement.setString(1, item.getNewsID());
            statement.setString(2, item.getText());
            statement.setLong(3, item.getPublicationDate());
            
            statement.executeUpdate();

        } finally {
            DatabaseUtility.getInstance().closeDatabaseObjects(results,
                    statement, conn);
        }
    }
    
    /**
     * 
     * @param text
     * @return
     * @throws SQLException
     * @throws NamingException 
     */
    public String insertNewsItem(final String text) throws SQLException,
            NamingException {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {

            conn = DatabaseUtility.getInstance().getDataSource()
                    .getConnection();
            statement = conn.prepareStatement(INSERT_NEWS_ITEM);

            java.util.Date rightNow = new java.util.Date();
            final String id = UUID.randomUUID().toString();
            
            statement.setString(1, id);
            statement.setString(2, text);
            statement.setLong(3, rightNow.getTime());
            
            statement.executeUpdate();
            
            return id;

        } finally {
            DatabaseUtility.getInstance().closeDatabaseObjects(results,
                    statement, conn);
        }
    }
}
