package com.example.calculatorplus.entity;

import java.util.List;

public class NumberVo {
    /**
     * 用户Id
     */
    private Integer mid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 时间
     */
    private String time;

    /**
     * 总金额
     */
    private Double totalMoney;

    /**
     * 记录
     */
    private List<NumberRecord> records;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<NumberRecord> getRecords() {
        return records;
    }

    public void setRecords(List<NumberRecord> records) {
        this.records = records;
    }
}
