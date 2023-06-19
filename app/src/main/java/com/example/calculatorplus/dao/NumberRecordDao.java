package com.example.calculatorplus.dao;

import androidx.room.*;
import com.example.calculatorplus.entity.NumberRecord;

import java.util.List;

@Dao
public interface NumberRecordDao {
    @Query("select * from NumberRecord")
    List<NumberRecord> getAll();

    @Insert
    void insert(NumberRecord ...numberRecords);

    @Update
    void update(NumberRecord ...numberRecords);

    @Delete
    void delete(NumberRecord ...numberRecords);
}
