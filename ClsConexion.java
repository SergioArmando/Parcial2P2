
package conexcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClsConexion {

    
     private static final String JDBC_URL = "jdbc:mysql://localhost:3306/basededatos?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
     private static final String JDBC_USER = "root";
     private static final String JDBC_PASSWORD = ""; //cambiale la clave si le tenes y en nombre de la base
     
     public static Connection OpenConection() throws SQLException{
          return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
     }
     public static void CloseConection(ResultSet rs){ 
          try{
               rs.close();
          }catch(SQLException ex){
               ex.printStackTrace(System.out);
          }
          
     }
      public static void CloseConection(PreparedStatement stmt){ 
          try{
               stmt.close();
          }catch(SQLException ex){
               ex.printStackTrace(System.out);
          }
          
     }
       public static void CloseConection(Connection conn){ 
          try{
               conn.close();
          }catch(SQLException ex){
               ex.printStackTrace(System.out);
          }
          
     }
     
    
}
