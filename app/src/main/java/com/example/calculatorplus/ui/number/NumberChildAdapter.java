package com.example.calculatorplus.ui.number;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.NumberRecord;

import java.util.List;

public class NumberChildAdapter extends BaseAdapter {
    private final List<NumberRecord> records;
    private final LayoutInflater inflater;

    public NumberChildAdapter(Context context, List<NumberRecord> records) {
        this.records = records;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int i) {
        return records.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_number_base_item, null);
            holder = new ViewHolder();
            holder.value = view.findViewById(R.id.number_value);
            holder.money = view.findViewById(R.id.number_money);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        NumberRecord record = records.get(i);
        if (record != null) {
            holder.value.setText(String.valueOf(record.getNumber()));
            holder.money.setText("¥" + record.getMoney());
        }
        return view;
    }

    static class ViewHolder {
        public TextView value;
        public TextView money;
    }
}
