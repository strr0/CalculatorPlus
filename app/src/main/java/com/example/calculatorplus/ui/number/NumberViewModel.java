package com.example.calculatorplus.ui.number;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.calculatorplus.dao.AppDao;
import com.example.calculatorplus.db.AppDatabase;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.entity.NumberRecord;

import java.util.List;
import java.util.Map;

public class NumberViewModel extends AndroidViewModel {
    private final AppDao appDao;
    private final LiveData<Map<MemberRecord, List<NumberRecord>>> liveData;

    public NumberViewModel(@NonNull Application application) {
        super(application);
        appDao = AppDatabase.getInstance(application).appDao();
        liveData = appDao.getMemNumbers();
    }

    public LiveData<Map<MemberRecord, List<NumberRecord>>> getLiveData() {
        return liveData;
    }

    public void save(List<NumberRecord> records) {
        if (records == null) {
            return;
        }
        for (NumberRecord record : records) {
            if (record.getId() != null) {
                appDao.updateNumber(record);
            } else {
                appDao.insertNumber(record);
            }
        }
    }

    public void remove(NumberRecord record) {
        appDao.deleteNumber(record);
    }
}
