package DataBase;

import java.sql.Connection;

public class DeleteDB {
    public static void deleteRecord(int id,String tableName){
        Connection c;

        try {
            c = DBConnection.connectDB();
            String query = "DELETE FROM "+tableName+" WHERE  id='"+id+"';";
            c.createStatement().execute(query);
        } catch (Exception e){
            System.out.println("Error in Building data in DeleteDB");
        }
    }
}
