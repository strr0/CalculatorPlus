package com.example.calculatorplus.ui.member;

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
import com.example.calculatorplus.view.DeleteAlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemberFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_member, container, false);
        initButton(root);
        initListView(root);
        return root;
    }

    private void initButton(View view) {
        FloatingActionButton fab = view.findViewById(R.id.member_add);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_member_edit);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initListView(View view) {
        MemberViewModel memberViewModel = new ViewModelProvider(this).get(MemberViewModel.class);
        ListView listView = view.findViewById(R.id.member_list);
        MemberAdapter adapter = new MemberAdapter(getActivity());
        listView.setAdapter(adapter);
        memberViewModel.getLiveData().observe(getViewLifecycleOwner(), records -> {
            adapter.setRecords(records);
            adapter.notifyDataSetChanged();  // 通知ListView刷新
        });
        // 长按删除事件
        listView.setOnItemLongClickListener((a, v, i, l) -> {
            AlertDialog dialog = DeleteAlertDialog.dialog(getActivity(), () -> {
                memberViewModel.remove((MemberRecord) adapter.getItem(i));
                adapter.notifyDataSetChanged();
                return null;
            });
            dialog.show();
            return true;
        });
        // 点击编辑事件
        listView.setOnItemClickListener((a, v, i, l) -> {
            Bundle bundle = new Bundle();
            MemberRecord record = (MemberRecord) adapter.getItem(i);
            bundle.putString("id", record.getId().toString());
            bundle.putString("name", record.getName());
            bundle.putString("phone", record.getPhone());
            Navigation.findNavController(v).navigate(R.id.nav_member_edit, bundle);
        });
    }
}
