package com.rohit.atm.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.atm.R;
import com.rohit.atm.adapter.AddMoneyAdapter;
import com.rohit.atm.model.Denomination;
import com.rohit.atm.model.DenominationManager;

import java.util.ArrayList;
import java.util.List;

public class AddMoney extends AppCompatActivity {

    private GridView gridView;
    private TextView addButton;
    private AddMoneyAdapter addMoneyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        gridView = findViewById(R.id.add_money_grid_view);
        addButton = findViewById(R.id.add_button);

        List<Denomination> mData = new ArrayList<>();
        for(int index = 0; index < DenominationManager.getInstance().getDenominationList().size(); index++) {
            mData.add(new Denomination(DenominationManager.getInstance().getDenominationList().get(index).getDenominationValue(), 0));
        }

        addMoneyAdapter = new AddMoneyAdapter(AddMoney.this, mData);
        gridView.setAdapter(addMoneyAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int position = 0; position < DenominationManager.getInstance().getDenominationList().size(); position++){
                    DenominationManager.getInstance().getDenominationList().get(position).addDenomination(addMoneyAdapter.getAddedCash().get(position).getDenominationCount());
                }
                Toast.makeText(AddMoney.this, "Cash Added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
