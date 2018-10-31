package at.accounting_otter;

import at.accounting_otter.entity.Transaction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class TransactionServiceImpl implements TransactionService{

    @Inject
    private DatabaseAdapter databaseAdapter;


    @Override
    public Transaction createTransaction(Transaction transaction) throws ObjectNotFoundException {
        if (databaseAdapter.getUser(transaction.getUser().getUserId()) == null) {
            throw new ObjectNotFoundException("User with id " + transaction.getUser().getUserId() + " not found.");
        }
        return databaseAdapter.createTransaction(transaction);
    }

    @Override
    public Transaction getTransaction(int transactionId) {
        return databaseAdapter.getTransaction(transactionId);
    }

    @Override
    public List<Transaction> getTransactions(int startIndex, int endIndex) throws IllegalArgumentException {
        if (endIndex > startIndex) {
            return databaseAdapter.getTransactions(startIndex, endIndex);
        } else {
            throw new IllegalArgumentException("endIndex must be greater than startIndex");
        }
    }

    @Override
    public Transaction updateTransaction(Transaction transactionUpdate) throws ObjectNotFoundException {
        if (databaseAdapter.getTransaction(transactionUpdate.getTransactionId()) == null) {
            throw new ObjectNotFoundException("Transaction with id " + transactionUpdate.getTransactionId() + " not found.");
        } else {
            // Make sure that the creator remains the owner of the transaction
            transactionUpdate.setUser(
                    databaseAdapter.getTransaction(transactionUpdate.getTransactionId()).getUser()
            );
            return databaseAdapter.updateTransaction(transactionUpdate);
        }
    }

    @Override
    public void deleteTransaction(int transactionId) throws ObjectNotFoundException {
        if (databaseAdapter.getTransaction(transactionId) == null) {
            throw new ObjectNotFoundException("Transaction with id " + transactionId + " not found.");
        } else {
            databaseAdapter.deleteTransaction(transactionId);
        }
    }

}
