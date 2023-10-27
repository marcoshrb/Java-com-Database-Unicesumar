package com.mycompany.atividademapa_programacao2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    
    private static Connection conn;
    
    public static Connection getConnection(){
        try{       
            if(conn == null){
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mapa", "root", "password");
                return conn;               
            } else{
                return conn;
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
              
    }
}
