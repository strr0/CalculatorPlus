package com.example.calculatorplus.ui.result;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.calculatorplus.R;

public class ResultFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result, container, false);
        initGridView(root);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initGridView(View view) {
        ResultViewModel resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        GridView gridView1 = view.findViewById(R.id.result_grid1);
        ResultAdapter adapter1 = new ResultAdapter(view.getContext());
        gridView1.setAdapter(adapter1);
        GridView gridView2 = view.findViewById(R.id.result_grid2);
        ResultAdapter adapter2 = new ResultAdapter(view.getContext());
        gridView2.setAdapter(adapter2);
        resultViewModel.getLiveData().observe(getViewLifecycleOwner(), records -> {
            resultViewModel.resetVoList();
            records.forEach(resultViewModel::addRecord);
            resultViewModel.setColor();
            adapter1.setVoList(resultViewModel.getVoList1());
            gridView1.setAdapter(adapter1);
            gridView1.deferNotifyDataSetChanged();
            adapter2.setVoList(resultViewModel.getVoList2());
            gridView2.setAdapter(adapter2);
            gridView2.deferNotifyDataSetChanged();
        });
    }
}
