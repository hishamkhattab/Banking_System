package sample;


import DataBase.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    /**
     * Variable of login page
     */
    @FXML
    private AnchorPane pane_login;
    @FXML
    private Button btn_login;
    @FXML
    private TextField txt_login_name;
    @FXML
    private PasswordField txt_password;
    @FXML
    private ComboBox<String> type;



    /**
     * Variable of Sign Up page
     */
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField txt_name_up;
    @FXML
    private TextField txt_phone_up;
    @FXML
    private TextField txt_email_up;
    @FXML
    private DatePicker txt_date_up;
    @FXML
    private ComboBox<String> type_up;
    @FXML
    private TextField txt_login_name_up;
    @FXML
    private TextField txt_password_up;
    @FXML
    private TextArea txt_details_up;

    public void setCustomer_id(int customer_id) {
        Controller.customer_id = customer_id;
    }

    private static int customer_id;

    public int getCustomer_id() {
        return Controller.customer_id;
    }

    /**
     * Variable to communicate with DB
     */
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;

    /**
     * Method to make the login pane visible when clicking on login button
     */
    public void loginPaneShow(){
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    /**
     * Method to make the sign up page visible when run the program
     */
    public void signupPaneShow(){
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }

    /**
     * Method accept login information from user, and check if it valid and drive him to next panel
     */
    @FXML
    private void login() {
        conn = DBConnection.connectDB();
        String sql = "select * from customers where login = ? and password = ? and customer_type  =?";
        try {

            pst = Objects.requireNonNull(conn).prepareStatement(sql);
            pst.setString(1, txt_login_name.getText());
            pst.setString(2,txt_password.getText());
            pst.setString(3,type.getValue());
            rs = pst.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"username and password are correct");
                setCustomer_id(rs.getInt(1));
                System.out.println("........customer_id:"+customer_id);
                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("account.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
            else {
                JOptionPane.showMessageDialog(null,"Invalid username or password");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Method to add a new customer
     */

  public void addUser(){


        conn = DBConnection.connectDB();
        String sql = "insert  into customers (customer_name,customer_phone,customer_email,date_become_customer,login,password,other_details,customer_type) values (?,?,?,?,?,?,?,?)";
            try {
                pst = Objects.requireNonNull(conn).prepareStatement(sql);
                pst.setString(1,txt_name_up.getText());
                pst.setString(2,txt_phone_up.getText());
                pst.setString(3,txt_email_up.getText());
                pst.setString(4, txt_date_up.getValue().toString());
                pst.setString(5,txt_login_name_up.getText());
                pst.setString(6,txt_password_up.getText());
                pst.setString(7,txt_details_up.getText());
                pst.setString(8, type_up.getValue());
                pst.execute();
                JOptionPane.showMessageDialog(null,"saved");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        type_up.getItems().addAll("1 ","2");
        type.getItems().addAll("1","2");
    }


}
