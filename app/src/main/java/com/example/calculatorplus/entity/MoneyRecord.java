package com.example.calculatorplus.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_money_record")
public class MoneyRecord {
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mon_id")
    private Integer id;

    /**
     * 金额
     */
    @ColumnInfo(name = "mon_money")
    private Double money;

    /**
     * 人员Id
     */
    @ColumnInfo(name = "mon_mid")
    private Integer mid;

    /**
     * 时间
     */
    @ColumnInfo(name = "mon_time")
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
