package com.wceii.newsfeed.servlets;

import com.wceii.newsfeed.NewsItem;
import com.wceii.newsfeed.TextFormatter;
import com.wceii.newsfeed.database.DBSelectUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author easonwc
 */
public class GetAllNewsServlet extends HttpServlet {

    /**
     * The serial version UID identifier.
     */
    private static final long serialVersionUID = 66490847509238745L;
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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<NewsItem> items = new ArrayList<>();
            try {
                items.addAll(DBSelectUtility.getInstance().getAllNewsItems());
            } catch (SQLException | NamingException ex) {
                LOGGER.error("problem getting news items", ex);
            }
            
            if(!items.isEmpty()) {
                Collections.sort(items);
                
                for(NewsItem tmpItem : items) {
                    out.println("<tr class=\"itemRow\">");
                    
                    out.println("<td class=\"itemColDate\">");
                    out.println(TextFormatter.getInstance()
                            .formatLongIntoDateString(tmpItem
                                    .getPublicationDate()));
                    out.println("</td>");
                    
                    out.println("<td class=\"itemColText\">");
                    out.println(tmpItem.getText());
                    out.println("</td>");
                    
                    out.println("</tr>");
                }
            }
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
