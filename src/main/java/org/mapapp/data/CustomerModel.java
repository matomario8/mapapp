package org.mapapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import org.mapapp.data.DatabaseConnection;
import org.mapapp.base.Customer;

public class CustomerModel {

    public static void createCustomer(String first, String last, String address, String city,
                                      String state, String zip, String phone, String email, String stable, boolean active) {

        try {
            Connection con = DatabaseConnection.initializeDatabase();

            String query = "INSERT INTO customers (c_first, c_last, c_address, "
                    + "c_city, c_state, c_zip, c_phone, c_email, c_stable, c_active) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, address);
            stmt.setString(4, city);
            stmt.setString(5, state);
            stmt.setString(6, zip);
            stmt.setString(7, phone);
            stmt.setString(8, email);
            stmt.setString(9, stable);
            stmt.setBoolean(10, active);

            stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> getCustomers() {

        List<Customer> customers = new ArrayList<>();

        try {
            Connection con = DatabaseConnection.initializeDatabase();

            String query = "SELECT * FROM customers";
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();


            while(rs.next()) {
                int id = rs.getInt("c_id");
                String first = rs.getString("c_first");
                String last = rs.getString("c_last");
                String address = rs.getString("c_address");
                String city = rs.getString("c_city");
                String state = rs.getString("c_state");
                String zip = rs.getString("c_zip");
                String phone = rs.getString("c_phone");
                String email = rs.getString("c_email");
                String stable = rs.getString("c_stable");
                boolean active = rs.getBoolean("c_active");

                customers.add(new Customer(id, first, last, address, city, state,
                        zip, phone, email, stable, active));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
}
