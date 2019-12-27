package com.rohit.atm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rohit.atm.R;
import com.rohit.atm.listeners.DenominationInvalidateListener;
import com.rohit.atm.model.Denomination;
import com.rohit.atm.model.DenominationManager;

import java.util.ArrayList;
import java.util.List;

public class InvalidateAdapter extends BaseAdapter {

    private Context mContext;
    private List<Denomination> mData = new ArrayList<>();
    // Gets the context so it can be used later
    public InvalidateAdapter(Context context, List<Denomination> mData) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_denominations, null);
            viewHolder = new ViewHolder();

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TextView denominationValue;
        ImageView delete;

        delete = convertView.findViewById(R.id.item_delete_image);
        denominationValue = convertView.findViewById(R.id.item_rupee_value);

        denominationValue.setText(String.valueOf(mData.get(position).getDenominationValue()));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DenominationInvalidateListener denominationInvalidateListener = (DenominationInvalidateListener) mContext;
                denominationInvalidateListener.onDenominationInvalidate(position);
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
