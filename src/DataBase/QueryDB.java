package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDB {

    static ResultSet rs;

    public static ResultSet query(String sql){
        Connection c = DBConnection.connectDB();
        try {
            rs = c.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("create statement in QueryDB catch an Error");
        }
        return rs;
    }
}
