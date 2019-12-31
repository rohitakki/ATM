package com.rohit.atm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rohit.atm.R;
import com.rohit.atm.model.AccountBalance;
import com.rohit.atm.model.DenominationManager;

public class CheckBalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);

        TextView totalBalance = findViewById(R.id.total_balance);
        TextView denominations = findViewById(R.id.denominations);

        String totalBalanceString = "Total Balance: " + AccountBalance.getInstance().getTotalBalance();
        totalBalance.setText(totalBalanceString);

        String denominationsListString = "";

        for(int index = 0; index < DenominationManager.getInstance().getDenominationList().size(); index++) {
            if(DenominationManager.getInstance().getDenominationList().get(index).getDenominationCount() > 0) {
                denominationsListString = denominationsListString + DenominationManager.getInstance().getDenominationList().get(index).getDenominationCount() + " * " + DenominationManager.getInstance().getDenominationList().get(index).getDenominationValue() + "\n";
            }
        }

        if(denominationsListString.equals("")) {
            denominations.setText(getString(R.string.no_money_denomination));
        } else {
            denominations.setText(denominationsListString);
        }
    }
}
