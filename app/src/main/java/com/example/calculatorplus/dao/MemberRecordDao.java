package com.example.calculatorplus.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.calculatorplus.entity.MemberRecord;

import java.util.List;

@Dao
public interface MemberRecordDao {
    @Query("select * from MemberRecord")
    List<MemberRecord> getAll();
}
