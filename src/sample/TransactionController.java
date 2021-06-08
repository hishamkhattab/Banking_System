package sample;


import DataBase.DBConnection;
import DataBase.DisplayDB;
import DataBase.QueryDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML
    private TextField accountNo;

    @FXML
    private ComboBox<String> transactionType;

    @FXML
    private TextField txtAmount;

    @FXML
    private Button addTransactionButton;

    @FXML
    private Label tWarnMsg;

    @FXML
    private DatePicker txtDate;






    DisplayDB transactionData = new DisplayDB();
    ObservableList<String> accountList = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeList.add("Deposit");
        typeList.add("Withdraw");
        typeList.add("Transfer");
        transactionType.setItems(typeList);


    }

    public void addTransaction(){
        LocalDate date = txtDate.getValue();
        String accountNumber = accountNo.getText();
        String type = transactionType.getValue();
        String amountText = txtAmount.getText();

        if(date==null){
            tWarnMsg.setText("Please Enter Date.");
            txtDate.requestFocus();
            return;
        }

        if(accountNumber==null || accountNumber.isEmpty()){
            tWarnMsg.setText("Please Enter Account Number.");
            accountNo.requestFocus();
            return;
        }

        if(type==null || type.isEmpty()){
            tWarnMsg.setText("Please Enter Transaction Type.");
            transactionType.requestFocus();
            return;
        }

        if(amountText==null || amountText.isEmpty()){
            tWarnMsg.setText("Please Enter Amount.");
            txtAmount.requestFocus();
        }

        double amount = Double.parseDouble(amountText);
        double balance = 0;
        try {
        ResultSet rs = QueryDB.query("select net_Balance from accounts where account_No ='"+accountNumber+"';");
        if (rs != null) {

            if (rs.next()) {
                balance = Double.parseDouble(rs.getString(1));
                if (type.equalsIgnoreCase("withdraw")) {
                    if (balance <= 0) {
                        tWarnMsg.setText("There is no enough balance");
                    }
                }
            } else {
                tWarnMsg.setText("Invalid account number");
            }
            }else {
                    tWarnMsg.setText("Invalid valid invalid");
            }
            Connection c;
            c = DBConnection.connectDB(); //transactions_id	transactions_date	account_No	transactions_type	amount
            String query = "INSERT INTO transactions (transactions_date,account_No,transactions_type,amount)VALUES("+
                    "'"+date+"',\n" +
                    "'"+accountNumber+"',\n" +
                    "'"+type+"',\n" +
                    "'"+amount+"');";
            c.createStatement().execute(query);
            if (type.equalsIgnoreCase("deposit")){
                balance+=amount;
            }
            else if (type.equalsIgnoreCase("withdraw")){
                balance-=amount;
            }
            query ="Update accounts SET net_Balance='"+balance+"' WHERE account_No='"+accountNumber+"';";
            c.createStatement().execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }



    public void deleteTransaction(ActionEvent event) {
    }
}


