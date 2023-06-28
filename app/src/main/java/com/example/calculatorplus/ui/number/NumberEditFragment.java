package com.example.calculatorplus.ui.number;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
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

public class NumberEditFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_number_edit, container, false);
        initSaveButton(root);
        initSpinner(root);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initSaveButton(View view) {
        NumberViewModel numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        EditText content = view.findViewById(R.id.number_edit_content);
        Spinner spinner = view.findViewById(R.id.number_edit_spinner);
        Button button = view.findViewById(R.id.number_save);
        button.setOnClickListener(v -> {
            String time = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            MemberRecord item = (MemberRecord) spinner.getSelectedItem();
            String[] nums = content.getText().toString().split(" ");
            for (String num : nums) {
                NumberRecord record = new NumberRecord();
                record.setMid(item.getId());
                record.setNumber(Integer.parseInt(num));
                record.setMoney(100d);
                record.setTime(time);
                numberViewModel.save(record);
            }
            Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).popBackStack();
        });
    }

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
