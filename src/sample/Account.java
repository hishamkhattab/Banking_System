package sample;

import DataBase.DBConnection;
import DataBase.DisplayDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class Account implements Initializable  {

    @FXML
    private TableView<AccountInfo> accountTable;

    @FXML
    private TableColumn<AccountInfo, Integer> accountNo;

    @FXML
    private TableColumn<AccountInfo, String> accountType;

    @FXML
    private TableColumn<AccountInfo, String> accountOpeningDate;

    @FXML
    private TableColumn<AccountInfo, Double> netBalance;

    @FXML
    private TableColumn<AccountInfo, Integer> id;
    @FXML
    private TableColumn<AccountInfo, Integer> customer_id;
    @FXML
    private VBox addUserPane;


    @FXML
    private Button updateInfoButton;

    @FXML
    private Button makeTransactionButton;

    @FXML
    private TextField newAccountType;

    @FXML
    private TextField newDate;

    @FXML
    private TextField newBalance;

    @FXML
    private Button addButton;

    Connection conn = null;
    PreparedStatement pst = null;
    ObservableList<AccountInfo> listM;

    public static int getAccount_id() {
        return account_id;
    }

    public static void setAccount_id(int account_id) {
        Account.account_id = account_id;
    }

    private static int account_id;


    public void updateTable(){
        //accountNo.setCellValueFactory(data -> data.getValue().userIdProperty().asString);
        //accountType.setCellValueFactory(data -> data.getValue().userIdProperty().asString);
        //accountOpeningDate.setCellValueFactory(data -> data.getValue().userIdProperty().asString);
        //netBalance.setCellValueFactory(data -> data.getValue().userIdProperty().asString);
            accountNo.setCellValueFactory(new PropertyValueFactory<AccountInfo, Integer>("accountNo"));
            accountType.setCellValueFactory(new PropertyValueFactory<AccountInfo, String>("accountType"));
            accountOpeningDate.setCellValueFactory(new PropertyValueFactory<AccountInfo, String>("accountOpeningDate"));
            netBalance.setCellValueFactory(new PropertyValueFactory<AccountInfo, Double>("netBalance"));
            customer_id.setCellValueFactory(new PropertyValueFactory<AccountInfo, Integer>("customer_id"));

            listM = DisplayDB.getDataUser();
            accountTable.setItems(listM);

    }

    public void addAccount(){
        conn = DBConnection.connectDB();
        String sql = "insert into accounts (account_No,account_Type,account_date,net_Balance)values(?,?,?,? )";
        try {
            pst = Objects.requireNonNull(conn).prepareStatement(sql);
            pst.setString(1,newAccountType.getText());
            pst.setString(1,newDate.getText());
            pst.setString(1,newBalance.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Users Add successfully");
            updateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void infoTablePaneShow(){
        updateTable();
        accountTable.setVisible(true);

    }
    public void addUserPaneShow(){
        addUserPane.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }


    public void updateAccountInfo(){
        updateInfoButton.getScene().getWindow().hide();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("updateInformation.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeTransaction(){
        makeTransactionButton.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("transaction.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
