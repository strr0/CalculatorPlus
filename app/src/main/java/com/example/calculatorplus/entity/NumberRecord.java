package com.example.calculatorplus.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_number_record")
public class NumberRecord {
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "num_id")
    private Integer id;

    /**
     * 数字
     */
    @ColumnInfo(name = "num_number")
    private Integer number;

    /**
     * 金额
     */
    @ColumnInfo(name = "num_money")
    private Double money;

    /**
     * 人员Id
     */
    @ColumnInfo(name = "num_mid")
    private Integer mid;

    /**
     * 类型
     */
    @ColumnInfo(name = "num_type")
    private String type;

    /**
     * 时间
     */
    @ColumnInfo(name = "num_time")
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
