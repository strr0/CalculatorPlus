package com.example.calculatorplus.ui.number;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.entity.NumberRecord;
import com.example.calculatorplus.ui.member.MemberViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberEditFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_number_edit, container, false);
        initGridView(root);
        initParseButton(root);
        initSpinner(root);
        initSaveButton(root);
        return root;
    }

    private void initGridView(View view) {
        GridView gridView = view.findViewById(R.id.number_edit_grid);
        NumberChildAdapter adapter = new NumberChildAdapter(getActivity());
        gridView.setAdapter(adapter);
    }

    private void initParseButton(View view) {
        Pattern pattern = Pattern.compile("\\d+");
        GridView gridView = view.findViewById(R.id.number_edit_grid);
        NumberChildAdapter adapter = (NumberChildAdapter) gridView.getAdapter();
        EditText content = view.findViewById(R.id.number_edit_content);
        Button button = view.findViewById(R.id.number_parse);
        button.setOnClickListener(v -> {
            Matcher matcher = pattern.matcher(content.getText().toString());
            List<NumberRecord> records = new ArrayList<>();
            NumberRecord record = null;
            while (matcher.find()) {
                if (record == null) {
                    record = new NumberRecord();
                    record.setNumber(Integer.parseInt(matcher.group()));
                } else {
                    record.setMoney(Double.parseDouble(matcher.group()));
                    records.add(record);
                    record = null;
                }
            }
            adapter.addRecords(records);
            adapter.notifyDataSetChanged();
        });
    }

    // 保存按鈕
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initSaveButton(View view) {
        NumberViewModel numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        GridView gridView = view.findViewById(R.id.number_edit_grid);
        NumberChildAdapter adapter = (NumberChildAdapter) gridView.getAdapter();
        Spinner spinner = view.findViewById(R.id.number_edit_spinner);
        Button button = view.findViewById(R.id.number_save);
        button.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle("保存")
                    .setMessage("是否保存?")
                    .setPositiveButton("確定", (d, i) -> {
                        String time = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                        MemberRecord item = (MemberRecord) spinner.getSelectedItem();
                        List<NumberRecord> records = adapter.getRecords();
                        records.forEach(record -> {
                            record.setMid(item.getId());
                            record.setTime(time);
                        });
                        numberViewModel.save(records);
                        Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(v).popBackStack();
                    })
                    .setNegativeButton("取消", (d, i) -> {
                        d.dismiss();
                    }).show();

        });
    }

    // 下拉框
    private void initSpinner(View view) {
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        Spinner spinner = view.findViewById(R.id.number_edit_spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getActivity());
        spinner.setAdapter(adapter);
        memberViewModel.getLiveData().observe(getViewLifecycleOwner(), records -> {
            adapter.setRecords(records);
            adapter.notifyDataSetChanged();  // 通知spinner刷新
        });
    }
}
