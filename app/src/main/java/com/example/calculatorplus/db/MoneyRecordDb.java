package com.example.calculatorplus.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.calculatorplus.dao.MoneyRecordDao;
import com.example.calculatorplus.entity.MoneyRecord;

@Database(entities = {MoneyRecord.class}, version = 1, exportSchema = false)
public abstract class MoneyRecordDb extends RoomDatabase {
    public abstract MoneyRecordDao moneyRecordDao();

    public static MoneyRecordDb INSTANCE;
    public static synchronized MoneyRecordDb getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MoneyRecordDb.class, "calculator_db").build();
        }
        return INSTANCE;
    }
}
