package com.example.calculatorplus.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.ResultVo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ResultFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result, container, false);
        initGridView(root);
        return root;
    }

    private void initGridView(View view) {
        // 数字集合
        List<ResultVo> voList1 = new ArrayList<>();
        List<ResultVo> voList2 = new ArrayList<>();
        List<ResultVo> bucket = new ArrayList<>();
        boolean flag = true;  // 是否voList1
        // 填充空白
        Calendar calendar = Calendar.getInstance();
        int year = (calendar.get(Calendar.YEAR) - 2020) % 12;
        for (int i = 0; i < year; i++) {
            bucket.add(new ResultVo());
            if (bucket.size() == 6) {
                if (flag) {
                    voList1.addAll(bucket);
                } else {
                    voList2.addAll(bucket);
                }
                bucket.clear();
                flag = !flag;
            }
        }
        // 填充数字
        for (int i = 1; i <= 48; i++) {
            ResultVo vo = new ResultVo();
            vo.setText(String.valueOf(i));
            bucket.add(vo);
            if (bucket.size() == 6) {
                if (flag) {
                    voList1.addAll(bucket);
                } else {
                    voList2.addAll(bucket);
                }
                bucket.clear();
                flag = !flag;
            }
        }
        if (!bucket.isEmpty()) {
            if (flag) {
                voList1.addAll(bucket);
            } else {
                voList2.addAll(bucket);
            }
            bucket.clear();
//            flag = !flag;
        }
        GridView mGridView1 = view.findViewById(R.id.result_grid1);
        ResultAdapter mResultAdapter1 = new ResultAdapter(view.getContext(), voList1);
        mGridView1.setAdapter(mResultAdapter1);
        GridView mGridView2 = view.findViewById(R.id.result_grid2);
        ResultAdapter mResultAdapter2 = new ResultAdapter(view.getContext(), voList2);
        mGridView2.setAdapter(mResultAdapter2);
    }
}
