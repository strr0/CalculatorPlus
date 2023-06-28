package com.example.calculatorplus.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.calculatorplus.entity.MemberRecord;
import com.example.calculatorplus.entity.MoneyRecord;
import com.example.calculatorplus.entity.NumberRecord;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;

@Dao
public interface AppDao {
    // member
    @Query("select * from t_member_record")
    LiveData<List<MemberRecord>> getMembers();

    @Insert
    ListenableFuture<Void> insetMembers(MemberRecord...records);

    @Update
    ListenableFuture<Void> updateMembers(MemberRecord...records);

    @Delete
    ListenableFuture<Void> deleteMembers(MemberRecord...records);

    // money
    @Query("select * from t_money_record")
    List<MoneyRecord> getMoneys();

    // number
    @Query("select * from t_number_record")
    LiveData<List<NumberRecord>> getNumbers();

    @Insert
    ListenableFuture<Void> insertNumbers(NumberRecord ...records);

    @Update
    ListenableFuture<Void> updateNumbers(NumberRecord ...records);

    @Delete
    ListenableFuture<Void> deleteNumbers(NumberRecord ...records);

    @Query("select * from t_member_record m " +
            "left join t_number_record n on m.mem_id = n.num_mid")
    LiveData<Map<MemberRecord, List<NumberRecord>>> getMemNumbers();
}
