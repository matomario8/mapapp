package org.mapapp.servlets;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapapp.base.Customer;
import org.mapapp.data.DatabaseConnection;

@WebServlet(name = "ToggleMarkerServlet", urlPatterns = {"toggle-marker"}, loadOnStartup = 1) 
public class ToggleMarkerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
        try {

            // TODO: Figure out what to do with the response - currently it is dummy data to return successful AJAX
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{}");

            // customerData should be a JSON.stringify object
            String customerData = request.getParameter("customer");
            Customer customer = new Gson().fromJson(customerData, Customer.class);

            // Values needed to make our database queries
            int customerId = customer.getId();
            boolean customerActive = customer.getActive();

            Connection con = DatabaseConnection.initializeDatabase();

            String query = "SELECT c_active FROM customers WHERE c_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();

            // TODO: Difference is not immediately obvious between customerActive and activeStatus
            boolean activeStatus;

            if(rs.next()) {
                activeStatus = rs.getBoolean("c_active");

                if(activeStatus == customerActive) {

                    query = "UPDATE customers SET c_active = ? WHERE c_id = ?";
                    stmt = con.prepareStatement(query);
                    stmt.setBoolean(1, !activeStatus);
                    stmt.setInt(2, customerId);
                    stmt.executeUpdate();

                }
            }

            stmt.close();
            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }




}