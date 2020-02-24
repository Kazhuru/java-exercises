package builders;

import models.Transaction;

public class TransactionBuilder {
    Transaction transaction;

    public TransactionBuilder() {
        this.transaction = new Transaction();
    }

    public Transaction build() {
        return transaction;
    }

    public TransactionBuilder account(long account) {
        transaction.setAccount(account);
        return this;
    }

    public TransactionBuilder amount(double amount) {
        transaction.setAmount(amount);
        return this;
    }

    public TransactionBuilder reference(String reference) {
        transaction.setReference(reference);
        return this;
    }

    public TransactionBuilder cardName(String cardName) {
        transaction.setCardName(cardName);
        return this;
    }

    public TransactionBuilder cardType(String cardType) {
        transaction.setCardType(cardType);
        return this;
    }
}
