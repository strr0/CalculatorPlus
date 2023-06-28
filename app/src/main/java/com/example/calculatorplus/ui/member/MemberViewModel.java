package com.example.calculatorplus.ui.member;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.calculatorplus.dao.AppDao;
import com.example.calculatorplus.db.AppDatabase;
import com.example.calculatorplus.entity.MemberRecord;

import java.util.List;

public class MemberViewModel extends AndroidViewModel {
    private final AppDao appDao;
    private final LiveData<List<MemberRecord>> liveData;

    public MemberViewModel(@NonNull Application application) {
        super(application);
        appDao = AppDatabase.getInstance(application).appDao();
        liveData = appDao.getMembers();
    }

    public LiveData<List<MemberRecord>> getLiveData() {
        return liveData;
    }

    public void save(MemberRecord record) {
        appDao.insetMembers(record);
    }

    public void update(MemberRecord record) {
        appDao.updateMembers(record);
    }

    public void remove(MemberRecord record) {
        appDao.deleteMembers(record);
    }
}
