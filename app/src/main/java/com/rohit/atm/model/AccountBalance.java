package com.rohit.atm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountBalance {

    private static AccountBalance mInstance;
    private double totalBalance = 0;

    public static AccountBalance getInstance(){
        if(mInstance == null){
            mInstance = new AccountBalance();
        }

        return mInstance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void addAmount(double addAmount) {
        this.totalBalance = this.totalBalance + addAmount;
    }

    public void deductAmount(double deductAmount){
        this.totalBalance = this.totalBalance - deductAmount;
    }

    public String withdrawCash(double amount) {
        List<Denomination> denominationList = new ArrayList<>();
        String withdrawString = "";
        double value = amount;
        for(int position = (DenominationManager.getInstance().getDenominationList().size() - 1); position >= 0; position--) {
            int denomination = DenominationManager.getInstance().getDenominationList().get(position).getDenominationValue();

            if(DenominationManager.getInstance().getDenominationList().get(position).getDenominationCount() >= (value/denomination)) {
                int count = (int) (value/denomination);
                value = value - (count * denomination);
                denominationList.add(new Denomination(denomination, count));
            } else {
                value = value - (DenominationManager.getInstance().getDenominationList().get(position).getDenominationCount() * denomination);
                denominationList.add(new Denomination(denomination, DenominationManager.getInstance().getDenominationList().get(position).getDenominationCount()));
            }
        }

        Collections.reverse(denominationList);
        if(value == 0) {
            for(int index = 0; index < denominationList.size(); index++) {
                if(denominationList.get(index).getDenominationCount() != 0) {
                    withdrawString = withdrawString + "\n" + String.valueOf(denominationList.get(index).getDenominationCount() + "*" + String.valueOf(denominationList.get(index).getDenominationValue()));
                    DenominationManager.getInstance().getDenominationList().get(index).deduceDenomination(denominationList.get(index).getDenominationCount());
                }
            }
        }

        return withdrawString;
    }
}
