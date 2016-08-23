package com.wceii.newsfeed.servlets;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author wceii
 */
public class InsertNewsServletTest {

    private static InitialContext context;

    public InsertNewsServletTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");

            context = new InitialContext();

            context.createSubcontext("java:");
            context.createSubcontext("java:comp");
            context.createSubcontext("java:comp/env");
            context.createSubcontext("java:comp/env/jdbc");

            final MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(System.getProperty("sqlUser"));
            dataSource.setPassword(System.getProperty("sqlPwd"));
            dataSource.setServerName(System.getProperty("sqlHost"));
            dataSource.setDatabaseName(System.getProperty("dbName"));

            context.bind("java:comp/env/jdbc/NewsFeedDB", dataSource);
        } catch (NamingException ex) {
            //ex.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownClass() {
        try {
            context.close();
        } catch (NamingException ex) {
            //ex.printStackTrace();
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of doPost method, of class InsertNewsServlet.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("newsDate", "");
        request.addParameter("newsData", "some great news story");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        response.setOutputStreamAccessAllowed(true);

        final InsertNewsServlet instance = new InsertNewsServlet();
        instance.doPost(request, response);

        final String contentReceivedFromServlet = response.getContentAsString();
        assertNotNull(contentReceivedFromServlet);
        assertTrue(!contentReceivedFromServlet.isEmpty());

        System.out.println(contentReceivedFromServlet);

        assertTrue(contentReceivedFromServlet.startsWith("{\"newsID\":"));
        assertTrue(contentReceivedFromServlet.contains("\"status\":\"success\""));
    }

}
