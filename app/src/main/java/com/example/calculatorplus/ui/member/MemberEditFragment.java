package com.example.calculatorplus.ui.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
                Toast.makeText(getActivity(), "名字不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            MemberRecord record = new MemberRecord();
            record.setName(name);
            record.setPhone(phone);
            memberViewModel.save(record);
            Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).popBackStack();
        });
    }
}
