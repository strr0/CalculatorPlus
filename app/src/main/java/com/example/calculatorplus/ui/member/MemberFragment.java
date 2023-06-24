package com.example.calculatorplus.ui.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.calculatorplus.R;
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
        adapter.setRecords(memberViewModel.getLiveData().getValue());
        listView.setAdapter(adapter);
        memberViewModel.getLiveData().observe(getViewLifecycleOwner(), adapter::setRecords);
    }
}
