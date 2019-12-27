package com.rohit.atm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.atm.R;
import com.rohit.atm.model.AccountBalance;

public class WithdrawCash extends AppCompatActivity {

    private EditText withdrawValue;
    private TextView withdrawButton, withdrawAmount;
    private TextView withdrawMainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_cash);

        withdrawValue = findViewById(R.id.withdraw_cash_value);
        withdrawButton = findViewById(R.id.withdraw_button);
        withdrawAmount = findViewById(R.id.withdrawn_amount);
        withdrawMainText = findViewById(R.id.main_text);

        if(AccountBalance.getInstance().getTotalBalance() == 0) {
            withdrawMainText.setText(getString(R.string.no_money));
        }

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(withdrawValue.getText() != null && !withdrawValue.getText().toString().trim().equals("") && Integer.valueOf(withdrawValue.getText().toString().trim()) > 0 && Integer.valueOf(withdrawValue.getText().toString().trim()) <= AccountBalance.getInstance().getTotalBalance()) {
                    String withdrawString = AccountBalance.getInstance().withdrawCash(Integer.valueOf(withdrawValue.getText().toString().trim()));
                    if(!withdrawString.matches("")) {
                        withdrawAmount.setVisibility(View.VISIBLE);
                        withdrawAmount.setText(withdrawString);
                        withdrawMainText.setText(getString(R.string.collect_cash));
                    } else {
                        withdrawAmount.setVisibility(View.GONE);
                        Toast.makeText(WithdrawCash.this, "Can't withdraw this amount", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    withdrawAmount.setVisibility(View.GONE);
                    Toast.makeText(WithdrawCash.this, "Can't withdraw this amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
