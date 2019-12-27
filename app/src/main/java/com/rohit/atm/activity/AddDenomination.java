package com.rohit.atm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.atm.R;
import com.rohit.atm.model.Denomination;
import com.rohit.atm.model.DenominationManager;

public class AddDenomination extends AppCompatActivity {

    private EditText denominationValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_denomination);

        denominationValue = findViewById(R.id.denomination_value);
        TextView addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(denominationValue.getText() != null && Integer.valueOf(denominationValue.getText().toString().trim()) > 0) {
                    for(int position = 0; position < DenominationManager.getInstance().getDenominationList().size(); position++) {
                        if(Integer.valueOf(denominationValue.getText().toString().trim()) < DenominationManager.getInstance().getDenominationList().get(position).getDenominationValue()) {
                            DenominationManager.getInstance().getDenominationList().add(position, new Denomination(Integer.valueOf(denominationValue.getText().toString().trim()), 0));
                            Toast.makeText(AddDenomination.this, "Denomination Added", Toast.LENGTH_SHORT).show();
                            break;
                        } else if(Integer.valueOf(denominationValue.getText().toString().trim()) > DenominationManager.getInstance().getDenominationList().get(position).getDenominationValue() && position == DenominationManager.getInstance().getDenominationList().size()-1) {
                            DenominationManager.getInstance().getDenominationList().add(new Denomination(Integer.valueOf(denominationValue.getText().toString().trim()), 0));
                            Toast.makeText(AddDenomination.this, "Denomination Added", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(AddDenomination.this, "Invalid Denomination", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
