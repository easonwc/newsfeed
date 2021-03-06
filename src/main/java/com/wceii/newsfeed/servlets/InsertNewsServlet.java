package com.wceii.newsfeed.servlets;

import com.wceii.newsfeed.ServletUtility;
import com.wceii.newsfeed.TextFormatter;
import com.wceii.newsfeed.database.DBInsertUtility;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author easonwc
 */
public class InsertNewsServlet extends HttpServlet {

    /**
     * The serial version UID identifier.
     */
    private static final long serialVersionUID = 6649034059867745L;
    /**
     *
     */
    private static final Logger LOGGER
            = Logger.getLogger(GetAllNewsServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.info("insert news servlet called");

        final String dateString = request.getParameter("newsDate");
        final String text = request.getParameter("newsData");

        LOGGER.info("insert news servlet parameters (date: " + dateString
                + " - text: " + text + ")");

        if (text != null && !text.isEmpty()) {
            try {
                //read date from parameter
                java.util.Date newsDate;
                if (dateString != null && !dateString.isEmpty()) {
                    try {
                        int gmtIndex = dateString.indexOf("GMT");
                        String dateStringCut = dateString.substring(0, gmtIndex);
                        LOGGER.info("date altered: " + dateStringCut);
                        newsDate = TextFormatter.getInstance()
                                .formatStringIntoDate(dateStringCut);
                    } catch (ParseException e) {
                        LOGGER.warn("invalid date parameter", e);
                        LOGGER.warn("using current time");
                        newsDate = new java.util.Date();
                    }
                } else {
                    newsDate = new java.util.Date();
                }

                String newsID = DBInsertUtility.getInstance()
                        .insertNewsItem(text, newsDate);

                LOGGER.info("inserted news item id: " + newsID);

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", "success");
                jsonObj.put("newsID", newsID);
                ServletUtility.getInstance().printValidJSONResponse(response,
                        jsonObj.toString());
            } catch (SQLException | NamingException ex) {
                LOGGER.error("problem inserting news item", ex);

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", "error");
                jsonObj.put("errorMessage", "problem inserting news item");
                ServletUtility.getInstance().printValidJSONResponse(response,
                        jsonObj.toString());
            }
        } else {
            LOGGER.warn("invalid or insufficient parameters");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
