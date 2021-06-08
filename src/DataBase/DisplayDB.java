package DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.AccountInfo;
import sample.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayDB {
    private  ObservableList<ObservableList> data;

    public static int account_No;
    public ObservableList<ObservableList> getData(){
        return data;
    }

    public static ObservableList<AccountInfo> getDataUser(){

        Connection conn = DBConnection.connectDB();
        ObservableList<AccountInfo> list = FXCollections.observableArrayList();
        Controller c = new Controller();
        int id = c.getCustomer_id();
        System.out.println("ddddddd:"+id);
        try {
            //String sql = "Select * from accounts";
            String sql = "SELECT * FROM `accounts` WHERE customer_id ='"+id+"';";
            PreparedStatement ps = null;
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            else
                System.out.println("a7teen");
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();

            }
            else
                System.out.println("3 a7aat");

            if (rs != null) {
                while (rs.next()){
                    list.add(new AccountInfo(Integer.parseInt(rs.getString("account_No")), rs.getString("account_Type"), rs.getString("account_date"), Double.parseDouble(rs.getString("net_Balance")),Integer.parseInt(rs.getString("customer_id"))));
                }
            }
            System.out.println("mfeeeeeesh");
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
/*
    public static ObservableList<AccountInfo> dateBuild(){

        Connection conn = DBConnection.connectDB();
        ObservableList<AccountInfo> list = FXCollections.observableArrayList();
        Controller c = new Controller();

        try {
            //String sql = "Select * from accounts";
            String sql = "SELECT * FROM `transactions` WHERE account_No ='"+account_No+"';";
            PreparedStatement ps = null;
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            else
                System.out.println("a7teen");
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();

            }
            else
                System.out.println("3 a7aat");

            if (rs != null) {
                while (rs.next()){
                    list.add(new AccountInfo(Integer.parseInt(rs.getString("account_No")), rs.getString("account_Type"), rs.getString("account_date"), Double.parseDouble(rs.getString("net_Balance")),Integer.parseInt(rs.getString("customer_id"))));
                }
            }
            System.out.println("mfeeeeeesh");
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
*/
/*

    public void buildData(TableView tableView, String sql){
        if (!tableView.getColumns().isEmpty()) {
            tableView.getColumns().clear();
        }
        Connection c;
        data = FXCollections.observableArrayList();

        try {
            c = DBConnection.connectDB();
            ResultSet rs = c.createStatement().executeQuery(sql);

            for (int i =0; i<rs.getMetaData().getColumnCount(); i++){
                final int j =i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory()
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
}

