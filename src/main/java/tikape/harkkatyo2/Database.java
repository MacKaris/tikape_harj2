/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harkkatyo2;

import java.sql.*;

 

public class Database {

 

    private String databaseAddress;

 

    public Database(String databaseAddress) throws ClassNotFoundException {

        this.databaseAddress = databaseAddress;

    }

 

    public Connection getConnection() throws SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }

        return DriverManager.getConnection(databaseAddress);

    }
    
    

}