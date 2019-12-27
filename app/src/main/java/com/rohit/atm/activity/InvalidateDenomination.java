package com.rohit.atm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.rohit.atm.R;
import com.rohit.atm.adapter.InvalidateAdapter;
import com.rohit.atm.listeners.DenominationInvalidateListener;
import com.rohit.atm.model.Denomination;
import com.rohit.atm.model.DenominationManager;

import java.util.ArrayList;
import java.util.List;

public class InvalidateDenomination extends AppCompatActivity implements DenominationInvalidateListener {

    private InvalidateAdapter invalidateAdapter;
    private GridView gridView;
    private List<Denomination> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalidate_denomination);

        gridView = findViewById(R.id.invalidate_grid_view);

        mData = DenominationManager.getInstance().getDenominationList();

        invalidateAdapter = new InvalidateAdapter(InvalidateDenomination.this, mData);
        gridView.setAdapter(invalidateAdapter);
    }

    @Override
    public void onDenominationInvalidate(int position) {
        DenominationManager.getInstance().invalidateDenomination(position);
        mData = DenominationManager.getInstance().getDenominationList();
        invalidateAdapter.notifyDataSetChanged();
    }
}