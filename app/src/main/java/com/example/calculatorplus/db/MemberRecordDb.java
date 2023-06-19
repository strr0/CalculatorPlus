package com.example.calculatorplus.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.calculatorplus.dao.MemberRecordDao;
import com.example.calculatorplus.entity.MemberRecord;

@Database(entities = {MemberRecord.class}, version = 1, exportSchema = false)
public abstract class MemberRecordDb extends RoomDatabase {
    public abstract MemberRecordDao memberRecordDao();

    private static MemberRecordDb INSTANCE;
    public static synchronized MemberRecordDb getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MemberRecordDb.class, "calculator_db").build();
        }
        return INSTANCE;
    }
}
