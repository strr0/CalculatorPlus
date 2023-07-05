package com.example.calculatorplus.ui.number;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.NumberRecord;
import com.example.calculatorplus.entity.NumberVo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_number, container, false);
        initButton(root);
        initListView(root);
        return root;
    }

    private void initButton(View view) {
        FloatingActionButton fab = view.findViewById(R.id.number_add);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_number_edit);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initListView(View view) {
        NumberViewModel numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        ListView listView = view.findViewById(R.id.number_list);
        NumberAdapter adapter = new NumberAdapter(getActivity());
        listView.setAdapter(adapter);
        numberViewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            List<NumberVo> voList = new ArrayList<>();
            data.forEach((m, ns) -> {
                ns.stream().collect(Collectors.groupingBy(NumberRecord::getTime)).forEach((t, records) -> {
                    NumberVo vo = new NumberVo();
                    vo.setMid(m.getId());
                    vo.setName(m.getName());
                    vo.setTime(t);  // 时间
                    vo.setRecords(records);
                    vo.setTotalMoney(records.stream().mapToDouble(NumberRecord::getMoney).sum());
                    voList.add(vo);
                });
            });
            adapter.setVoList(voList);
            adapter.notifyDataSetChanged();
        });
        // 点击事件
        listView.setOnItemClickListener((a, v, i, l) -> {
            Bundle bundle = new Bundle();
            NumberVo vo = (NumberVo) adapter.getItem(i);
            bundle.putInt("mid", vo.getMid());
            bundle.putString("time", vo.getTime());
            Navigation.findNavController(v).navigate(R.id.nav_number_edit, bundle);
        });
    }
}
