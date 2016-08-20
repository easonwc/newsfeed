package com.wceii.newsfeed;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author weason
 */
public class ServletUtility {

    /**
     * The servlet JSON response type.
     */
    private static final String JSON_CONTENT_TYPE
            = "application/json;charset=UTF-8";
    /**
     * The servlet plain text response type.
     */
    private static final String PLAIN_TEXT_CONTENT_TYPE
            = "text/plain;charset=UTF-8";
    /**
     * Servlet response for invalid request.
     */
    private static final String REQUEST_INAVLID_ERROR_MESSAGE
            = "Error!!!\nRequest Invalid...";
    /**
     * Servlet response for server internal error.
     */
    private static final String SERVER_ERROR_MESSAGE = "Error!!!...";
    /**
     * Singleton instance.
     */
    private static ServletUtility instance;

    /**
     * An empty constructor.
     */
    private ServletUtility() {

    }
    
    /**
     * 
     * @return 
     */
    public static synchronized ServletUtility getInstance() {
        if (instance == null) {
            instance = new ServletUtility();
        }

        return instance;
    }

    /**
     * This method will print a valid JSON response to a servlet response.
     *
     * @param response - The servlet response handler to print to.
     * @param text - The JSON text to write.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void printValidJSONResponse(
            final HttpServletResponse response, final String text)
            throws IOException {

        final PrintWriter out = response.getWriter();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(JSON_CONTENT_TYPE);
        response.setHeader("Cache-Control", "no-cache");

        out.print(text);
    }

    /**
     * This method will print an error message if the request for a servlet is
     * incorrect, meaning if the parameters are not complete or incorrectly
     * formatted.
     *
     * @param response - The servlet response handler to print to.
     *
     * @throws IOException if an I/O error occurs
     */
    public void printInvalidRequestResponse(
            final HttpServletResponse response) throws IOException {

        final PrintWriter out = response.getWriter();

        response.setContentType(PLAIN_TEXT_CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        out.println(REQUEST_INAVLID_ERROR_MESSAGE);
    }

    /**
     * This method will print an error message if the servlet code has an
     * internal error.
     *
     * @param response - The servlet response handler to print to.
     *
     * @throws IOException if an I/O error occurs
     */
    public void printServerErrorResponse(
            final HttpServletResponse response) throws IOException {

        final PrintWriter out = response.getWriter();

        response.setContentType(PLAIN_TEXT_CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        out.println(SERVER_ERROR_MESSAGE);
    }
}
