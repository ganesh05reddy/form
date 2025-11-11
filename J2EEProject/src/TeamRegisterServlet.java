import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeamRegisterServlet
 */
@WebServlet("/TeamRegisterServlet")
public class TeamRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set character encoding to handle all types of characters
        request.setCharacterEncoding("UTF-8");

        try {
            // Getting input values from the HTML form
            String teamName = request.getParameter("teamName");
            String iglName = request.getParameter("iglName");
            String phone = request.getParameter("phone");
            String player1 = request.getParameter("player1");
            String player2 = request.getParameter("player2");
            String player3 = request.getParameter("player3");
            String player4 = request.getParameter("player4");

            // DB connection details (Corrected URL)
            String url = "jdbc:mysql://localhost:3306/lab_3";
            String username = "cs23bt002";
            String password = "Gani@123"; // Make sure this is your correct password

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            // Check if Team Name already exists in the 'teams' table
            PreparedStatement check = con.prepareStatement("SELECT * FROM teams WHERE team_name = ?");
            check.setString(1, teamName);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                // Team name is not taken, proceed with insertion
                PreparedStatement st = con.prepareStatement("INSERT INTO teams (team_name, igl_name, phone, player1, player2, player3, player4) VALUES (?, ?, ?, ?, ?, ?, ?)");
                
                st.setString(1, teamName);
                st.setString(2, iglName);
                st.setString(3, phone);
                st.setString(4, player1);
                st.setString(5, player2);
                st.setString(6, player3);
                st.setString(7, player4);
                
                int result = st.executeUpdate();

                // If insertion successful, forward to RegisterResult.jsp
                if (result > 0) {
                    RequestDispatcher rd = request.getRequestDispatcher("RegisterResult.jsp");
                    rd.forward(request, response);
                }
                st.close();
                
            } else {
                // Team name is already taken, forward to an error page or back to the form
                
                // Set an error message to display on the form
                request.setAttribute("errorMessage", "The team name '" + teamName + "' is already taken. Please choose another.");
                
                // Forward back to the registration page
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp"); // or .html
                rd.forward(request, response);
            }

            rs.close();
            check.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Also forward to an error page if a database exception occurs
            request.setAttribute("errorMessage", "A database error occurred: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp"); // or .html
            rd.forward(request, response);
        }
    }
}