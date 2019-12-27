package com.rohit.atm.model;

public class Denomination {

    private int denominationValue;
    private int denominationCount;

    public Denomination(int denominationValue, int denominationCount) {
        this.denominationValue = denominationValue;
        this.denominationCount = denominationCount;
    }

    public int getDenominationValue() {
        return denominationValue;
    }

    public void setDenominationValue(int denominationValue) {
        this.denominationValue = denominationValue;
    }

    public int getDenominationCount() {
        return this.denominationCount;
    }

    public void setDenominationCount(int denominationCount) {
        this.denominationCount = denominationCount;
    }

    public void addDenomination(int denominationCount){
        this.denominationCount = this.denominationCount + denominationCount;
        AccountBalance.getInstance().addAmount(denominationCount * this.denominationValue);
    }

    public void deduceDenomination(int denominationCount) {
        this.denominationCount = this.denominationCount - denominationCount;
        AccountBalance.getInstance().deductAmount(denominationCount * this.denominationValue);
    }
}
