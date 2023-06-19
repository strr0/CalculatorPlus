package com.example.calculatorplus.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class NumberRecord {
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    /**
     * 数字
     */
    private Integer number;

    /**
     * 金额
     */
    private Double money;

    /**
     * 人员Id
     */
    private Integer mid;

    /**
     * 类型
     */
    private String type;

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

//    public Date getTime() {
//        return time;
//    }
//
//    public void setTime(Date time) {
//        this.time = time;
//    }
}
