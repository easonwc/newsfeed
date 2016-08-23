package com.wceii.newsfeed.database;

import com.wceii.newsfeed.NewsItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.naming.NamingException;

/**
 *
 * @author easonwc
 */
public class DBSelectUtility {

    /**
     *
     */
    private static DBSelectUtility instance;
    /**
     *
     */
    private static final String GET_ALL_NEWS_ITEMS = "select * from NewsItem "
            + "limit 250";

    /**
     * Empty constructor.
     */
    private DBSelectUtility() {

    }

    /**
     *
     * @return
     */
    public static synchronized DBSelectUtility getInstance() {
        if (instance == null) {
            instance = new DBSelectUtility();
        }

        return instance;
    }

    /**
     *
     * @return @throws SQLException
     * @throws NamingException
     */
    public Set<NewsItem> getAllNewsItems() throws SQLException,
            NamingException {

        Set<NewsItem> items = new HashSet<>();

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {

            conn = DatabaseUtility.getInstance().getDataSource()
                    .getConnection();
            statement = conn.prepareStatement(GET_ALL_NEWS_ITEMS);

            results = statement.executeQuery();

            while (results.next()) {
                NewsItem tmpItem = new NewsItem();

                tmpItem.setNewsID(results.getString(1));
                tmpItem.setText(results.getString(2));
                tmpItem.setPublicationDate(results.getLong(3));

                items.add(tmpItem);
            }

            return items;
        } finally {
            DatabaseUtility.getInstance().closeDatabaseObjects(results,
                    statement, conn);
        }
    }
}
