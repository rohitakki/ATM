package com.rohit.atm.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.rohit.atm.R;
import com.rohit.atm.model.Denomination;
import com.rohit.atm.model.DenominationManager;

import java.util.ArrayList;
import java.util.List;

public class AddMoneyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Denomination> mData = new ArrayList<>();
    // Gets the context so it can be used later
    public AddMoneyAdapter(Context context, List<Denomination> mData) {
        mContext = context;
        this.mData = mData;
    }

    // Total number of things contained within the adapter
    @Override
    public int getCount() {
        return mData.size();
    }

    // Require for structure, not really used in my code.
    @Override
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position,
                        View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_add_denomination, null);
            viewHolder = new ViewHolder();

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TextView denominationValue;
        ImageView deduction;
        ImageView addition;
        final EditText denominationCount;

        denominationValue = convertView.findViewById(R.id.item_rupee_value);
        deduction = convertView.findViewById(R.id.deduction);
        addition = convertView.findViewById(R.id.addition);
        denominationCount = convertView.findViewById(R.id.count_edit_text);

        denominationValue.setText(String.valueOf(mData.get(position).getDenominationValue()));

        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(denominationCount.getText().toString().trim());
                if(count > 0) {
                    count = count - 1;
                    denominationCount.setText(String.valueOf(count));
                    mData.get(position).setDenominationCount(count);
                }
            }
        });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(denominationCount.getText().toString().trim());
                count = count + 1;
                denominationCount.setText(String.valueOf(count));
                mData.get(position).setDenominationCount(count);
            }
        });

        denominationCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().equals("")) {
                    mData.get(position).setDenominationCount(Integer.valueOf(s.toString().trim()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return convertView;
    }

    public List<Denomination> getAddedCash(){
        return mData;
    }

    class ViewHolder {
    }
}
