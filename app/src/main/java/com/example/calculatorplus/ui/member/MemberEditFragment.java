package com.example.calculatorplus.ui.member;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;

public class MemberEditFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_member_edit, container, false);
        initButton(root);
        return root;
    }

    private void initButton(View view) {
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        Button button = view.findViewById(R.id.member_save);
        EditText nameText = view.findViewById(R.id.member_edit_name);
        EditText phoneText = view.findViewById(R.id.member_edit_phone);
        button.setOnClickListener(v -> {
            String name = nameText.getText().toString();
            String phone = phoneText.getText().toString();
            if ("".equals(name)) {
                showSaveResultDialog("失败");
                return;
            }
            MemberRecord record = new MemberRecord();
            record.setName(name);
            record.setPhone(phone);
            memberViewModel.save(record);
            showSaveResultDialog("成功");
            Navigation.findNavController(v).popBackStack();
        });
    }

    private void showSaveResultDialog(String msg) {
        new AlertDialog.Builder(getActivity())
                .setTitle("保存结果")
                .setMessage(msg)
                .setPositiveButton("确定", null)
                .show();
    }
}
