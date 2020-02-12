package com.luxoft.bankapp.model;

import com.luxoft.bankapp.service.feed.Feed;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractAccount implements Account, Serializable
{
    private double balance;

    final AccountType type;

    public AbstractAccount(AccountType type)
    {
        this.type = type;
    }

    @Override
    public void deposit(double amount)
    {
        if (amount < 0)
        {
            return;
        }

        balance += amount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        AbstractAccount that = (AbstractAccount) o;

        return Double.compare(that.balance, balance) == 0;

    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder
                .append("\n")
                .append(getType())
                .append("\n\tbalance = ")
                .append(balance);
        return builder.toString();
    }

    @Override
    public AccountType getType()
    {
        return type;
    }

    @Override
    @Feed("BALANCE")
    public double getBalance()
    {
        return balance;
    }

    void setBalance(double balance)
    {
        this.balance = balance;
    }

    // TODO feed
    public void parseFeed(Map<String, String> map)
    {
        try
        {
            String balance = map.get("balance");
            this.balance = Double.parseDouble(balance != null ? balance : "");
        }
        catch (NumberFormatException ignore)
        {

        }
    }


}
