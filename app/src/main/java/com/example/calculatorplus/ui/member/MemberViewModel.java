package com.example.calculatorplus.ui.member;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.calculatorplus.dao.MemberRecordDao;
import com.example.calculatorplus.db.MemberRecordDb;
import com.example.calculatorplus.entity.MemberRecord;

import java.util.List;

public class MemberViewModel extends AndroidViewModel {
    private final MemberRecordDao memberRecordDao;
    private final LiveData<List<MemberRecord>> liveData;

    public MemberViewModel(@NonNull Application application) {
        super(application);
        memberRecordDao = MemberRecordDb.getInstance(application).memberRecordDao();
        liveData = memberRecordDao.getAll();
    }

    public LiveData<List<MemberRecord>> getLiveData() {
        return liveData;
    }

    public void save(MemberRecord record) {
        memberRecordDao.inset(record);
    }

    public void update(MemberRecord record) {
        memberRecordDao.update(record);
    }

    public void remove(MemberRecord record) {
        memberRecordDao.delete(record);
    }
}
