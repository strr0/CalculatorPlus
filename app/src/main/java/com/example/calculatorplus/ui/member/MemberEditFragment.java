package com.example.calculatorplus.ui.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        initData(root);
        return root;
    }

    private void initButton(View view) {
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        Button button = view.findViewById(R.id.member_save);
        EditText idText = view.findViewById(R.id.member_edit_id);
        EditText nameText = view.findViewById(R.id.member_edit_name);
        EditText phoneText = view.findViewById(R.id.member_edit_phone);
        button.setOnClickListener(v -> {
            String id = idText.getText().toString();
            String name = nameText.getText().toString();
            String phone = phoneText.getText().toString();
            if ("".equals(name)) {
                Toast.makeText(getActivity(), "名字不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            MemberRecord record = new MemberRecord();
            record.setName(name);
            record.setPhone(phone);
            if ("".equals(id)) {
                memberViewModel.save(record);
            } else {
                record.setId(Integer.parseInt(id));
                memberViewModel.update(record);
            }
            Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).popBackStack();
        });
    }

    private void initData(View view) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        TextView avatarText = view.findViewById(R.id.member_edit_avatar);
        EditText idText = view.findViewById(R.id.member_edit_id);
        EditText nameText = view.findViewById(R.id.member_edit_name);
        EditText phoneText = view.findViewById(R.id.member_edit_phone);
        String name = bundle.getString("name");
        idText.setText(bundle.getString("id"));
        nameText.setText(name);
        phoneText.setText(bundle.getString("phone"));
        avatarText.setText(name.substring(name.length() - 1));
    }
}
