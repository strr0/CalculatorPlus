package com.example.calculatorplus.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.calculatorplus.entity.MoneyRecord;

import java.util.List;

@Dao
public interface MoneyRecordDao {
    @Query("select * from MoneyRecord")
    List<MoneyRecord> getAll();
}
