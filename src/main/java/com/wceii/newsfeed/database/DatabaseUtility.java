package com.wceii.newsfeed.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author easonwc
 */
public class DatabaseUtility {

    /**
     * Data source context identifier.
     */
    private static final String DATASOURCE_CONTEXT = "jdbc/NewsFeedDB";
    /**
     * Initial Context identifier.
     */
    private static final String INITIAL_CONTEXT = "java:comp/env";
    /**
     * The internal data source connection.
     */
    private static DataSource ds;
    /**
     * Singleton instance.
     */
    private static DatabaseUtility instance;

    /**
     * Constructor that initializes the shared data source.
     *
     * @throws NamingException - if there is a JNDI error.
     */
    private DatabaseUtility() throws NamingException {
        initializeDataSource();
    }

    /**
     *
     * @return @throws NamingException
     */
    public static synchronized DatabaseUtility getInstance()
            throws NamingException {
        if (instance == null) {
            instance = new DatabaseUtility();
        }

        return instance;
    }

    /**
     * Internal method to initialize the database connection.
     *
     * @throws NamingException - if a JNDI error occurs.
     */
    private static synchronized void initializeDataSource()
            throws NamingException {

        if (ds == null) {
            final Context initCtx = new InitialContext();
            final Context envCtx = (Context) initCtx
                    .lookup(INITIAL_CONTEXT);
            ds = (DataSource) envCtx.lookup(DATASOURCE_CONTEXT);
        }
    }
    
    /**
     * 
     * @return 
     */
    public DataSource getDataSource() {
        return ds;
    }

    /**
     * This method will close all database objects in the proper order.
     *
     * @param resultSet - The result set.
     * @param statement - The prepared statement.
     * @param conn - The database connection.
     *
     * @throws java.sql.SQLException - if a database error occurs.
     */
    public void closeDatabaseObjects(final ResultSet resultSet,
            final PreparedStatement statement, final Connection conn)
            throws SQLException {

        if (resultSet != null) {
            resultSet.close();
        }

        closeDatabaseObjects(statement, conn);
    }

    /**
     * This method will close all database objects in the proper order.
     *
     * @param statement - The prepared statement.
     * @param conn - The database connection.
     *
     * @throws java.sql.SQLException - if a database error occurs
     */
    public void closeDatabaseObjects(final PreparedStatement statement,
            final Connection conn) throws SQLException {

        if (statement != null) {
            statement.close();
        }

        if (conn != null) {
            conn.close();
        }
    }
}
