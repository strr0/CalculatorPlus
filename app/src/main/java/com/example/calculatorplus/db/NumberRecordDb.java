package com.example.calculatorplus.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.calculatorplus.dao.NumberRecordDao;
import com.example.calculatorplus.entity.NumberRecord;

@Database(entities = {NumberRecord.class}, version = 1, exportSchema = false)
public abstract class NumberRecordDb extends RoomDatabase {
    public abstract NumberRecordDao numberRecordDao();

    private static NumberRecordDb INSTANCE;
    public static synchronized NumberRecordDb getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NumberRecordDb.class, "calculator_db").build();
        }
        return INSTANCE;
    }
}
