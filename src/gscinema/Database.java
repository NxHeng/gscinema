/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gscinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author e-hen
 */
public class Database {
    Connection connection;
    //connect with database
    public void connect() throws SQLException{
        try{
            
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/gsc";
            String dbusername = "root";
            String dbpassword = "root";
            
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url,dbusername,dbpassword);
            
	}catch (Exception e) {
            System.out.println(e.getMessage());
	}
    }
    
    //return connection
    public Connection getConnection(){
        return connection;
    }
}
