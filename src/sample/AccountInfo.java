package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountInfo implements Initializable {
    private int accountNo;
    private String accountType;
    private String accountOpeningDate;
    private double netBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public AccountInfo(int accountNo, String accountType, String accountOpeningDate, double netBalance, int id) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.accountOpeningDate = accountOpeningDate;
        this.netBalance = netBalance;
        this.id = id;
    }

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(String accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
