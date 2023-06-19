package com.example.calculatorplus.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class MoneyRecord {
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    /**
     * 金额
     */
    private Double money;

    /**
     * 人员Id
     */
    private Integer mid;

    /**
     * 时间
     */
//    private Date time;

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

//    public Date getTime() {
//        return time;
//    }
//
//    public void setTime(Date time) {
//        this.time = time;
//    }
}
