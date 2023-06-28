package com.example.calculatorplus.ui.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.ResultVo;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<ResultVo> voList;

    public ResultAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        voList = new ArrayList<>();
    }

    public void setVoList(List<ResultVo> voList) {
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
            view = inflater.inflate(R.layout.fragment_result_item, null);
            holder = new ViewHolder();
            holder.text = view.findViewById(R.id.result_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ResultVo vo = voList.get(i);
        if (vo != null) {
            holder.text.setText(vo.getText());
            view.setBackgroundColor(vo.getColor());
        }
        return view;
    }

    static class ViewHolder {
        public TextView text;
    }
}
