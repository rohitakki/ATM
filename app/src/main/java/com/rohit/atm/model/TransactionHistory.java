package com.rohit.atm.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {

    private static TransactionHistory mInstance;
    private List<Double> history = new ArrayList<>();

    public static TransactionHistory getInstance(){
        if(mInstance == null) {
            mInstance = new TransactionHistory();
        }

        return mInstance;
    }

    public List<Double> getHistory() {
        return history;
    }

    public void addTransaction(double transaction) {
        history.add(transaction);
    }

}
