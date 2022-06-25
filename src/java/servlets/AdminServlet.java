
package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        // to read files        
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        // Create new session
        HttpSession session = request.getSession();
        
        // Declare and Initialize variables
        String line;
        int total = 0;
        int expensivePrice = 0;
        String expensiveItem = "";
        String expensiveUser = "";
        
        // Read through homeitems.txt and calculate total for every user, the 
        // most expensive item, and the user with the most expensive item.
        while ((line = br.readLine()) != null) {
            String[] items;
            items = line.split(",");
            total += Integer.parseInt(items[3]);
                if (expensivePrice < Integer.parseInt(items[3])) {
                    expensivePrice = Integer.parseInt(items[3]);
                    expensiveItem = items[2];
                    expensiveUser = items[0];
                }
        }
            
        // Print total for every user
        request.setAttribute("adminTotal", total);
        
        // Print most expensive item, user and price
        request.setAttribute("mostExpensive", "Most expensive item is " + expensiveItem +
                " at $" + expensivePrice + " owned by " + expensiveUser + ".");
        br.close();
        
        // Check if user is admin. If true, open admin.jsp
        if (session.getAttribute("user").equals("admin") ) {
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request,response);
            return;
        } else { // Else redirect to InventoryServlet
            response.sendRedirect("inventory");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
}
