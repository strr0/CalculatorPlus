package com.example.calculatorplus.ui.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<MemberRecord> records;

    public MemberAdapter(Context context) {
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
            view = inflater.inflate(R.layout.fragment_member_item, null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.member_name);
            holder.phone = view.findViewById(R.id.member_phone);
            holder.state = view.findViewById(R.id.member_state);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MemberRecord record = records.get(i);
        if (record != null) {
            holder.name.setText(record.getName());
            holder.phone.setText(record.getPhone());
            holder.state.setText(record.getState());
        }
        return view;
    }

    static class ViewHolder {
        public TextView name;
        public TextView phone;
        public TextView state;
    }
}
