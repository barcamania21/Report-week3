
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Objects;


public class Account{
    private String accountNumber;

    public Account(String accountNumber){
        if (accountNumber == null){
            throw new NullPointerException("accountNumber is null");
        }
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public ArrayList getTransactions() throws SQLException {

        List dbTransactionList = Db.getTransactions(accountNumber.trim());
        ArrayList transactionList = new ArrayList();

        for (int i = 0; i < dbTransactionList.size(); i++) {
            DbRow dbRow = (DbRow) dbTransactionList.get(i);
            Transaction trans = makeTransactionFromDbRow(dbRow);
            transactionList.add(trans);
            return transactionList;
        }
    }
    private Transaction makeTransactionFromDbRow(DbRow row){
        double currencyAmountInPounds = Double.parseDouble(row.getValueForField("amt"));
        String description = row.getValueForField("desc");
        return new Transaction(description, currencyAmountInPounds); 
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if((obj == null) || (!(this instanceof Account)) ){
            return false;
        }

        Account otherAccount = (Account) obj;
        return ((Account) obj).getAccountNumber().equals(getAccountNumber());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getAccountNumber());
    }
}
