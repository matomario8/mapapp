package org.mapapp.servlets;

import org.mapapp.servlets.ToggleMarkerServlet;
import org.mapapp.base.Customer;
import org.mapapp.data.DatabaseConnection;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ToggleMarkerServletTest {

    @Test
    public void testRequestParameterFields() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ToggleMarkerServlet toggleMarkerServlet = new ToggleMarkerServlet();

        when(request.getParameter("customer")).thenReturn("{\"id\": \"777\",\"first\": \"firstname\","
                + "\"last\": \"lastington\",\"address\": \"22 something lane\",\"city\": \"new york\",\"state\": \"NY\","
                + "\"zip\": \"10122\",\"email\": \"flastington@gmail.com\",\"phone\": \"777-757-1243\","
                + "\"stable\": \"somewhere\",\"active\": \"true\"}");

        Customer sampleCustomer = new Customer(1, "firstname", "lastington", "22 something lane", "new york", "NY", "10122",
                "777-757-1243", "flastington@gmail.com", "somewhere", true);

        toggleMarkerServlet.doPost(request, response);

        assertEquals(200, response.getStatus());
    }

}
