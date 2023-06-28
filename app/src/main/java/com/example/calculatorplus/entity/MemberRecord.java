package com.example.calculatorplus.entity;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "t_member_record")
public class MemberRecord {
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mem_id")
    private Integer id;

    /**
     * 名称
     */
    @ColumnInfo(name = "mem_name")
    private String name;

    /**
     * 电话
     */
    @ColumnInfo(name = "mem_phone")
    private String phone;

    /**
     * 状态
     */
    @ColumnInfo(name = "mem_state")
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberRecord record = (MemberRecord) o;
        return Objects.equals(id, record.id);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
