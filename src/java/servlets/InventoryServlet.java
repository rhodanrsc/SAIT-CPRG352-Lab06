
package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Create new session
        HttpSession session = request.getSession();
        
        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        // to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        // Declare variables
        String line;
        int total = 0;
        
        // Read through the users.txt and calculate total for user
        while ((line = br.readLine()) != null) {
            String[] items;
            items = line.split(",");

            if (items[0].equals(session.getAttribute("user"))) {
                total += Integer.parseInt(items[3]);
            }
        }
        
        // Print total message
        request.setAttribute("totalMessage", "Total value in inventory: $" + total);
        
        // Open inventory.jsp
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Create new session
        HttpSession session = request.getSession();
        
        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        // to append to a file
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true))); 
        
        // Initialize variables
        String user = (String) session.getAttribute("user");
        String category = request.getParameter("category");
        String item = request.getParameter("item_name");
        String price = request.getParameter("item_price");
        String line;
        int total = 0;
        
        // Open BufferedReader
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        // Read through homeitems.txt and calculate total for user
        while ((line = br.readLine()) != null) {
            String[] items;
            items = line.split(",");

            if (items[0].equals(session.getAttribute("user"))) {
                total += Integer.parseInt(items[3]);
            }
        }
        
        // Print new total for user
        request.setAttribute("totalMessage", "Total value in inventory: $" + total);
        
        // Check if inputs are blank. If true, print error message
        if (category.equals("") || item.equals("") || price.equals("") ) {
            pw.close();
            request.setAttribute("errorMessage", "Invalid. Please re-enter.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;
        }
        // Check if price is within range and not blank. If true, print success message and add to homeitems.txt
        if (Integer.parseInt(price) > 0 && Integer.parseInt(price) < 10000 && item != null) {
            pw.println(user + "," + category + "," + item + "," + price);
            pw.close();
            request.setAttribute("successMessage", "The item was successfully added to your inventory.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;
        } else { // If false, print error message
            pw.close();
            request.setAttribute("errorMessage", "Invalid. Please re-enter.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;
        }
        
        
    }

}
