package com.example.calculatorplus.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.calculatorplus.entity.MemberRecord;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface MemberRecordDao {
    @Query("select * from MemberRecord")
    LiveData<List<MemberRecord>> getAll();

    @Insert
    ListenableFuture<Void> inset(MemberRecord...memberRecords);
}
