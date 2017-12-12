package ohtu.data_access;

import java.sql.*;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(databaseAddress);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return conn;
        
    }
}
