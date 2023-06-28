package com.example.calculatorplus.ui.number;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.NumberRecord;
import com.example.calculatorplus.entity.NumberVo;

import java.util.ArrayList;
import java.util.List;

public class NumberAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<NumberVo> voList;

    public NumberAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        voList = new ArrayList<>();
    }

    public void setVoList(List<NumberVo> voList) {
        this.voList.clear();
        if (voList != null) {
            this.voList.addAll(voList);
        }
    }

    @Override
    public int getCount() {
        return voList.size();
    }

    @Override
    public Object getItem(int i) {
        return voList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_number_base, null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.number_name);
            holder.totalMoney = view.findViewById(R.id.number_totalMoney);
            holder.time = view.findViewById(R.id.number_time);
            holder.records = view.findViewById(R.id.number_grid);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        NumberVo vo = voList.get(i);
        if (vo != null) {
            holder.name.setText(vo.getName());
            holder.totalMoney.setText(String.valueOf(vo.getTotalMoney()));
            holder.time.setText(vo.getTime());
            List<NumberRecord> records = vo.getRecords();
            NumberChildAdapter numberChildAdapter = new NumberChildAdapter(view.getContext(), records);
            holder.records.setAdapter(numberChildAdapter);
        }
        return view;
    }

    static class ViewHolder {
        public TextView name;
        public TextView totalMoney;
        public TextView time;
        public GridView records;
    }
}
