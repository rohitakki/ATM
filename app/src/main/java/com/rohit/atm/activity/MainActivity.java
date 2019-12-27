package com.rohit.atm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rohit.atm.R;
import com.rohit.atm.model.AccountBalance;
import com.rohit.atm.model.Denomination;
import com.rohit.atm.model.DenominationManager;

public class MainActivity extends AppCompatActivity {

    private TextView currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDefaultDenomination();
        init();
    }

    private void addDefaultDenomination() {
        DenominationManager.getInstance().addDenomination(new Denomination(50, 0));
        DenominationManager.getInstance().addDenomination(new Denomination(100, 0));
        DenominationManager.getInstance().addDenomination(new Denomination(200, 0));
        DenominationManager.getInstance().addDenomination(new Denomination(500, 0));
        DenominationManager.getInstance().addDenomination(new Denomination(2000, 0));

        currentBalance = findViewById(R.id.current_balance);
        currentBalance.setText(String.valueOf(AccountBalance.getInstance().getTotalBalance()));
    }

    private void init() {
        TextView addMoney = findViewById(R.id.atm_add_cash);
        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMoney.class));
            }
        });

        TextView addDenomination = findViewById(R.id.add_new_denomination);
        addDenomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddDenomination.class));
            }
        });

        TextView withdrawCash = findViewById(R.id.atm_withdraw_cash);
        withdrawCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WithdrawCash.class));
            }
        });

        TextView invalidate = findViewById(R.id.invalidate_denomination);
        invalidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InvalidateDenomination.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentBalance.setText(String.valueOf(AccountBalance.getInstance().getTotalBalance()));
    }
}
