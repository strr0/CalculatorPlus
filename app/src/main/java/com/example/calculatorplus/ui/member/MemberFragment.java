package com.example.calculatorplus.ui.member;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemberFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_member, container, false);
        initButton(root);
        initModelView(root);
        return root;
    }

    private void initButton(View view) {
        FloatingActionButton fab = view.findViewById(R.id.member_add);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_member_edit);
        });
    }

    private void initModelView(View view) {
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        ListView listView = view.findViewById(R.id.member_list);
        MemberAdapter adapter = new MemberAdapter(getActivity());
        listView.setAdapter(adapter);
        memberViewModel.getLiveData().observe(getViewLifecycleOwner(), records -> {
            adapter.setRecords(records);
            adapter.notifyDataSetChanged();  // 通知ListView刷新
        });
        // 长按删除按钮
        listView.setOnItemLongClickListener((a, v, i, l) -> {
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
            // 自定义确认框
            View confirm = View.inflate(getActivity(), R.layout.fragment_member_del, null);
            EditText pwdText = confirm.findViewById(R.id.member_del_pwd);
            Button ok = confirm.findViewById(R.id.member_del_ok);
            Button cancel = confirm.findViewById(R.id.member_del_cancel);
            ok.setOnClickListener(v1 -> {
                if ("abc123".equals(pwdText.getText().toString())) {
                    memberViewModel.remove((MemberRecord) adapter.getItem(i));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "已删除", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "密码错误", Toast.LENGTH_LONG).show();
                }
            });
            cancel.setOnClickListener(v2 -> {
                dialog.dismiss();
            });
            dialog.setView(confirm);
            dialog.show();
            return false;
        });
    }
}
