package at.accounting_otter;

import at.accounting_otter.entity.Transaction;

import javax.inject.Inject;

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
    public Transaction updateTransaction(Transaction transaction) throws ObjectNotFoundException {
        if (databaseAdapter.getTransaction(transaction.getTransactionId()) == null) {
            throw new ObjectNotFoundException("Transaction with id " + transaction.getTransactionId() + " not found.");
        } else if (databaseAdapter.getUser(transaction.getUser().getUserId()) == null) {
            throw new ObjectNotFoundException("User with id " + transaction.getUser().getUserId() + " not found.");
        } else {
            return databaseAdapter.updateTransaction(transaction);
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
