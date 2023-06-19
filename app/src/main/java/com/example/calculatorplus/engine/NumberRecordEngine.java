package com.example.calculatorplus.engine;

import android.content.Context;
import com.example.calculatorplus.dao.NumberRecordDao;
import com.example.calculatorplus.db.NumberRecordDb;

public class NumberRecordEngine {
    private NumberRecordDao numberRecordDao;

    public NumberRecordEngine(Context context) {
        NumberRecordDb numberRecordDb = NumberRecordDb.getInstance(context);
        numberRecordDao = numberRecordDb.numberRecordDao();
    }
}
