
package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        if (request.getParameter("logout") != null) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            return;
            
        } else if (session.getAttribute("user") != null ) {
            response.sendRedirect("inventory");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = getServletContext().getRealPath("/WEB-INF/users.txt");
        // to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        // Create new session
        HttpSession session = request.getSession(); 
        
        // Declare and Initialize variables
        String username = request.getParameter("user_in");
        String password = request.getParameter("pass_in");
            
        String line;
        
        // Read through the users.txt
        while ((line = br.readLine()) != null) {
            String[] login;
            login = line.split(",");
            
            // Check if user input is one of the logins in users.txt
            if (login[0].equals(username) && login[1].equals(password)) {
                session.setAttribute("user", username);
                // Check if user is admin. If true, redirect to AdminServlet
                if (login[0].equals("admin")) {
                    response.sendRedirect("admin");
                    return;
                }
                // Redirect to inventory if not admin
                response.sendRedirect("inventory");
                return; 
            } else { // Print error message if login invalid
                request.setAttribute("message", "Invalid login.");  
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        br.close();
    }
}
