package com.wceii.newsfeed.servlets;

import com.wceii.newsfeed.ServletUtility;
import com.wceii.newsfeed.database.DBInsertUtility;
import java.io.IOException;
import java.sql.SQLException;
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
        
        final String date = request.getParameter("newsDate");
        final String text = request.getParameter("newsData");
        
        if(date != null && !date.isEmpty() && text != null && !text.isEmpty()) {
            try {
                //TODO read date from parameter
                String newsID = DBInsertUtility.getInstance().insertNewsItem(text);
                
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", "success");
                jsonObj.put("newsID", newsID);
                ServletUtility.printValidJSONResponse(response, jsonObj.toString());
            } catch (SQLException | NamingException ex) {
                LOGGER.error("problem inserting news item", ex);
                
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", "error");
                jsonObj.put("errorMessage", "problem inserting news item");
                ServletUtility.printValidJSONResponse(response, jsonObj.toString());
            }
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