package com.example.calculatorplus.ui.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.calculatorplus.R;
import com.example.calculatorplus.entity.MemberRecord;

import java.util.ArrayList;
import java.util.List;

public class MemberFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_member, container, false);
        initListView(root);
        return root;
    }

    private void initListView(View view) {
        List<MemberRecord> records = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MemberRecord record = new MemberRecord();
            record.setName("Alan");
            record.setPhone("666-666-666");
            record.setState("up");
            records.add(record);
        }
        ListView listView = view.findViewById(R.id.member_list);
        MemberAdapter adapter = new MemberAdapter(view.getContext(), records);
        listView.setAdapter(adapter);
    }
}
