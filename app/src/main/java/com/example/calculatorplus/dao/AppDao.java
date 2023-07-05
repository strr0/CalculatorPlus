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
    ListenableFuture<Void> insetMember(MemberRecord record);

    @Update
    ListenableFuture<Void> updateMember(MemberRecord record);

    @Delete
    ListenableFuture<Void> deleteMember(MemberRecord record);

    // money
    @Query("select * from t_money_record")
    List<MoneyRecord> getMoneys();

    // number
    @Query("select * from t_number_record")
    LiveData<List<NumberRecord>> getNumbers();

    @Insert
    ListenableFuture<Void> insertNumber(NumberRecord record);

    @Update
    ListenableFuture<Void> updateNumber(NumberRecord record);

    @Delete
    ListenableFuture<Void> deleteNumber(NumberRecord record);

    @Query("select * from t_member_record m " +
            "left join t_number_record n on m.mem_id = n.num_mid")
    LiveData<Map<MemberRecord, List<NumberRecord>>> getMemNumbers();

    @Query("select * from t_number_record m " +
            "where m.num_mid = :mid and m.num_time = :time")
    LiveData<List<NumberRecord>> getNumberByMen(Integer mid, String time);
}
