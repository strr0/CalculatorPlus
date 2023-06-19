package com.example.calculatorplus.ui.number;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.NumberRecord;
import com.example.calculatorplus.entity.NumberVo;

import java.util.ArrayList;
import java.util.List;

public class NumberFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_number, container, false);
        initListView(root);
        return root;
    }

    private void initListView(View view) {
        List<NumberVo> voList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NumberVo vo = new NumberVo();
            vo.setName("Alan");
            vo.setTotalMoney(500d);
            List<NumberRecord> records = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                NumberRecord record = new NumberRecord();
                record.setNumber(30 + j);
                record.setMoney(i * 10 + 10d);
                records.add(record);
            }
            vo.setRecords(records);
            voList.add(vo);
        }
        ListView listView = view.findViewById(R.id.number_list);
        NumberAdapter adapter = new NumberAdapter(view.getContext(), voList);
        listView.setAdapter(adapter);
    }
}
