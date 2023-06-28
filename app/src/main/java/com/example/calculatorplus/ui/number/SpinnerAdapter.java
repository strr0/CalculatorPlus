package com.example.calculatorplus.ui.number;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.ui.member.MemberAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<MemberRecord> records;

    public SpinnerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        records = new ArrayList<>();
    }

    public void setRecords(List<MemberRecord> records) {
        this.records.clear();
        if (records != null) {
            this.records.addAll(records);
        }
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
            view = inflater.inflate(R.layout.fragment_number_spinner_item, null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.spinner_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MemberRecord record = records.get(i);
        if (record != null) {
            holder.name.setText(record.getName());
        }
        return view;
    }

    static class ViewHolder {
        public TextView name;
    }
}
