package com.example.calculatorplus.ui.result;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.calculatorplus.dao.AppDao;
import com.example.calculatorplus.db.AppDatabase;
import com.example.calculatorplus.entity.NumberRecord;
import com.example.calculatorplus.entity.ResultVo;

import java.util.*;

public class ResultViewModel extends AndroidViewModel {
    private final AppDao appDao;
    private final LiveData<List<NumberRecord>> liveData;

    // 数字集合
    private List<ResultVo> voList1;
    private List<ResultVo> voList2;
    private Map<Integer, ResultVo> voMap;

    public ResultViewModel(@NonNull Application application) {
        super(application);
        appDao = AppDatabase.getInstance(application).appDao();
        liveData = appDao.getNumbers();
        // 初始化数据
        initData();
    }

    public LiveData<List<NumberRecord>> getLiveData() {
        return liveData;
    }

    // 初始化数据
    private void initData() {
        voList1 = new ArrayList<>();
        voList2 = new ArrayList<>();
        voMap = new HashMap<>();
        List<ResultVo> bucket = new ArrayList<>();
        boolean flag = true;  // 是否voList1
        // 填充空白
        Calendar calendar = Calendar.getInstance();
        int year = (calendar.get(Calendar.YEAR) - 2020) % 12;
        for (int i = 0; i < year; i++) {
            bucket.add(new ResultVo());
            if (bucket.size() == 6) {
                if (flag) {
                    voList1.addAll(bucket);
                } else {
                    voList2.addAll(bucket);
                }
                bucket.clear();
                flag = !flag;
            }
        }
        // 填充数字
        for (int i = 1; i <= 48; i++) {
            ResultVo vo = new ResultVo();
            vo.setText(String.valueOf(i));
            bucket.add(vo);
            if (bucket.size() == 6) {
                if (flag) {
                    voList1.addAll(bucket);
                } else {
                    voList2.addAll(bucket);
                }
                bucket.clear();
                flag = !flag;
            }
            voMap.put(i, vo);
        }
        if (!bucket.isEmpty()) {
            if (flag) {
                voList1.addAll(bucket);
            } else {
                voList2.addAll(bucket);
            }
            bucket.clear();
//            flag = !flag;
        }
    }

    public List<ResultVo> getVoList1() {
        return voList1;
    }

    public List<ResultVo> getVoList2() {
        return voList2;
    }

    public void resetVoList() {
        for (ResultVo item : voMap.values()) {
            item.setTotalMoney(0d);
            item.clearRecord();
            item.setColor(0xFFFFFFFF);
        }
    }

    public void addRecord(NumberRecord record) {
        ResultVo vo = voMap.get(record.getNumber());
        if (vo != null) {
            vo.addRecord(record);
            vo.setTotalMoney(vo.getTotalMoney() + record.getMoney());
        }
    }

    public void setColor() {
        for (ResultVo item : voMap.values()) {
            if (item.getTotalMoney() >= 500) {
                item.setColor(0xFFF56C6C);
            } else if (item.getTotalMoney() >= 200) {
                item.setColor(0xFFE6A23C);
            }
        }
    }
}
