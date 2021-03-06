package controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(MainServlet.class);

    private Helper helper = Helper.getInstance();

    public MainServlet() {
    }

    // Parsing command and forwarding to proper page
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;

        try {
            ICommand command = helper.getCommand(request);
            page = command.execute(request, response);
            logger.error("Redirect to "+page);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            page = "/error.jsp";
        }
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}