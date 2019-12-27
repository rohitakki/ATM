package com.rohit.atm.model;

import java.util.ArrayList;
import java.util.List;

public class DenominationManager {

    private List<Denomination> denominationList = new ArrayList<>();
    private static DenominationManager mInstance;

    public static DenominationManager getInstance(){
        if(mInstance == null){
            mInstance = new DenominationManager();
        }
        return mInstance;
    }

    public List<Denomination> getDenominationList() {
        return denominationList;
    }

    public void addDenomination(Denomination newDenomination){
        denominationList.add(newDenomination);
        AccountBalance.getInstance().addAmount(newDenomination.getDenominationValue() * newDenomination.getDenominationCount());
    }

    public void invalidateDenomination(int position){
        AccountBalance.getInstance().deductAmount(denominationList.get(position).getDenominationValue() * denominationList.get(position).getDenominationCount());
        denominationList.remove(position);
    }
}