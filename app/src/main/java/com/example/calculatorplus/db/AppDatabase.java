package com.example.calculatorplus.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.calculatorplus.dao.AppDao;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.entity.MoneyRecord;
import com.example.calculatorplus.entity.NumberRecord;

@Database(entities = {MemberRecord.class, MoneyRecord.class, NumberRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();

    private static AppDatabase INSTANCE;
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "calculator_db").build();
        }
        return INSTANCE;
    }
}
