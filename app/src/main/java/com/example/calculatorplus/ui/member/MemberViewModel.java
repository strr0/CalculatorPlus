package com.example.calculatorplus.ui.member;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.calculatorplus.dao.AppDao;
import com.example.calculatorplus.db.AppDatabase;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.entity.NumberRecord;

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
        appDao.insetMember(record);
    }

    public void update(MemberRecord record) {
        appDao.updateMember(record);
    }

    public void remove(MemberRecord record) {
        appDao.deleteMember(record);
    }

    public LiveData<List<NumberRecord>> getNumberLiveData(Integer mid, String time) {
        return appDao.getNumberByMen(mid, time);
    }
}
