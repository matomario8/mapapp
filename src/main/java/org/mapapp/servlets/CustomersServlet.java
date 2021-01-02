package org.mapapp.servlets;

import java.util.List;
import java.io.IOException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapapp.base.Customer;
import org.mapapp.data.CustomerModel;

@WebServlet(name = "CustomersServlet", urlPatterns = {"get-customers"}, loadOnStartup = 1)
public class CustomersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Customer> customers = CustomerModel.getCustomers();
        String customerData = new Gson().toJson(customers);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(customerData);
        
    }
}