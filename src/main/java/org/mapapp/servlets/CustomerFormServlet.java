package org.mapapp.servlets;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapapp.data.CustomerModel;

@WebServlet(name = "CustomerFormServlet", urlPatterns = {"customer-form-submit"}, loadOnStartup = 1) 
public class CustomerFormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String first = request.getParameter("customer-first");
        String last = request.getParameter("customer-last");
        String address = request.getParameter("customer-address");
        String city = request.getParameter("customer-city");
        String state = request.getParameter("customer-state");
        String zip = request.getParameter("customer-zip");
        String phone = request.getParameter("customer-phone");
        String email = request.getParameter("customer-email");
        String stable = request.getParameter("customer-stable");
        boolean active = false;

        CustomerModel.createCustomer(first, last, address, city, state, zip, phone, email, stable, active);

        request.setAttribute("customerfirst", first);
        request.setAttribute("customerlast", last);

        request.getRequestDispatcher("customer-form-submit.jsp").forward(request, response);
        
    }

}