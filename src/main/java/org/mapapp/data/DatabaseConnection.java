package org.mapapp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException {
        
        String dbDriver = "";
        String dbURL = "";

        //TODO: put these fields in a config file
        String dbName = "";
        String dbUsername = "";
        String dbPassword = "";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);

        return con;
    }

}
