package at.accounting_otter;

import at.accounting_otter.entity.Debit;

import javax.inject.Inject;
import java.util.List;

public class DebitServiceImpl implements DebitService {

    @Inject
    DatabaseAdapter databaseAdapter;

    @Override
    public Debit createDebit(Debit debit) throws ObjectNotFoundException{
        if (databaseAdapter.getUser(debit.getPayer().getUserId()) == null) {
            throw new ObjectNotFoundException("User with id " + debit.getPayer().getUserId() + " not found.");
        } else if (databaseAdapter.getUser(debit.getDebtor().getUserId()) == null) {
            throw new ObjectNotFoundException("User with id " + debit.getDebtor().getUserId() + " not found.");
        }

        return databaseAdapter.createDebit(debit);
    }

    @Override
    public Debit getDebit(int debitId) {
        return databaseAdapter.getDebit(debitId);
    }

    @Override
    public List<Debit> getDebitsByTransactionId(int transactionId) {
        return databaseAdapter.getDebitsByTransactionId(transactionId);
    }

    @Override
    public double getSumAmountByTransactionId(int transactionId) {
        return databaseAdapter.getSumAmountByTransactionId(transactionId);
    }

    @Override
    public Debit updateDebit(Debit debit) throws ObjectNotFoundException {
        if (databaseAdapter.getDebit(debit.getDebitId()) == null) {
            throw new ObjectNotFoundException("Debit with id " + debit.getDebitId() + " not found.");
        } else if (databaseAdapter.getUser(debit.getPayer().getUserId()) == null) {
            throw new ObjectNotFoundException("User with id " + debit.getPayer().getUserId() + " not found.");
        } else if (databaseAdapter.getUser(debit.getDebtor().getUserId()) == null) {
            throw new ObjectNotFoundException("User with id " + debit.getDebtor().getUserId() + " not found.");
        } else {
            return databaseAdapter.updateDebit(debit);
        }
    }

    @Override
    public void deleteDebit(int debitId) throws ObjectNotFoundException {
        if (databaseAdapter.getDebit(debitId) == null) {
            throw new ObjectNotFoundException("Debit with id " + debitId + " not found.");
        } else {
            databaseAdapter.deleteDebit(debitId);
        }
    }

}
