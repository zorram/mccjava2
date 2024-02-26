package org.example.mmschulzfinalproject;

import java.math.BigDecimal;

public class Account {
    private int id;
    private BigDecimal balance;
    private BigDecimal interestRate;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

}
