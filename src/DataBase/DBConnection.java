package DataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    private static Connection conn;
    public static Connection connectDB(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_management","root","");
            JOptionPane.showMessageDialog(null,"Connection Established");
            return conn;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }



}
